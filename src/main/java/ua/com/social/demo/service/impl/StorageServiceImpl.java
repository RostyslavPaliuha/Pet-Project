package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.service.api.StorageService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
    private static final Logger LOG = Logger.getLogger(StorageServiceImpl.class);
    private static final String MAIN_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive";
    private String images = "\\images\\avatar";
    private String previews = "\\images\\previews";
    private String audio = "\\audios";
    private String video = "\\videos";
    @Autowired
    PasswordEncoder encoder;

    public StorageServiceImpl() {
    }

    @Override
    public boolean saveFile(MultipartFile file, String path) throws Exception {
        byte[] bytes = file.getBytes();
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(MAIN_PATH + path + file.getOriginalFilename())))) {
            stream.write(bytes);
            return true;
        } catch (IOException e) {
            LOG.error("Error while system try to write " + file.getOriginalFilename());
            throw new Exception("\"Error while system try to write \" + file.getOriginalFilename()", e);
        }

    }

    public void savePreview(MultipartFile srcFile, String path) throws IOException {
        BufferedImage image = ImageIO.read(srcFile.getInputStream());
        BufferedImage resized = GraphicServiceImpl.resize(image, 100, 100);
        File output = new File(
                System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive\\" + path + "previews\\min-"
                        + srcFile.getOriginalFilename());
        ImageIO.write(resized, "png", output);
    }

    @Override
    public boolean mkDirForNewUser(Integer profileId) throws IOException {
        //String UserMainDirName=encoder.encode(profileId.toString()); TO DO SOMETHING WITH HASH!
        String[] media = {images, audio, video, previews};
        Arrays.stream(media).forEach(s -> new File(MAIN_PATH + "\\" + profileId.toString() + s).mkdirs());
        return true;
    }

    @Override
    public boolean checkMainPath() throws IOException {
        return Files.exists(Paths.get(MAIN_PATH));
    }

    @Override
    public boolean createMainPath() {
        new File(MAIN_PATH).mkdir();
        return true;
    }

    public List getFilesNamesFromDir(String path) {
        List files = new ArrayList();
        try {
            Files.list(Paths.get(MAIN_PATH + path))
                    .filter(Files::isRegularFile)
                    .forEach(file -> files.add(file));
            return files;
        } catch (IOException e) {
            LOG.error("Failed when create file list from dir: " + e.getMessage());
        }
        return files;
    }

    public InputStreamResource prepareFileForDownload(Integer profileId, String photoName) throws FileNotFoundException {
        InputStreamResource resource = null;
        try {
            Path pathToPhoto = Paths.get(MAIN_PATH + "\\" + profileId + "\\images\\" + photoName);
            resource = new InputStreamResource(Files.newInputStream(pathToPhoto));
            return resource;
        } catch (IOException e) {
            throw new FileNotFoundException("No matched files found.");
        }
    }

    public Map<String,String> downloadPreviews(String path)  {
        Map<String,String> encodedfile = new HashMap<>();
        Path previews = Paths.get(MAIN_PATH + path);
        try {
            Files.list(previews)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            encodedfile.put(file.getFileName().toString(),new String(Base64.encodeBase64String(Files.readAllBytes(file))));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return encodedfile;
    }
}


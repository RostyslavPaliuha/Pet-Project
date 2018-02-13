package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.service.api.StorageService;

import javax.imageio.IIOException;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service("storageService")
public class StorageServiceImpl implements StorageService {
    private static final Logger LOG = Logger.getLogger(StorageServiceImpl.class);
    private static final String MAIN_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\userFilesArchive";

    public StorageServiceImpl() {
    }

    @Override
    public boolean saveFile(MultipartFile file) throws IOException {
        try {
            byte[] bytes = file.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(System.getProperty("user.dir") + "\\src\\main\\resources\\uploaded\\photo\\" + file.getOriginalFilename())));
            stream.write(bytes);
            stream.close();
            return true;
        } catch (IOException e) {
            LOG.error("Error while system try to write " + file.getOriginalFilename());
            throw e;
        }

    }

    @Override
    public boolean mkDirForNewUser() throws IIOException {

        return false;
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

}


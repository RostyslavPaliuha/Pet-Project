package ua.com.social.demo.service.api;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StorageService {
    public boolean saveFile(MultipartFile file, String path) throws Exception;

    public void savePreview(MultipartFile srcFile, String path) throws IOException;

    public boolean mkDirForNewUser(Integer profileId) throws IOException;

    public boolean checkMainPath() throws IOException;

    public boolean createMainPath() throws IOException;

    public List getFilesNamesFromDir(String path);

    public InputStreamResource prepareFileForDownload(Integer profileId, String photoName) throws IOException;

    public Map<String,String> downloadPreviews(String path);
}

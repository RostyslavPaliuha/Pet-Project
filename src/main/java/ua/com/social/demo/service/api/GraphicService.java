package ua.com.social.demo.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface GraphicService {
    public void resizeImgAndSave(MultipartFile file) throws IOException;

    public Map<String, List<File>> readAllProfileImg(Integer profileId);

    public Map<String, List<File>> readAllImagesFromDir();
}

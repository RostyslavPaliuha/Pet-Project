package ua.com.social.demo.service.api;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface GraphicService {


    public Map<String, List<File>> readAllProfileImg(Integer profileId);

    public Map<String, List<File>> readAllImagesFromDir();
}

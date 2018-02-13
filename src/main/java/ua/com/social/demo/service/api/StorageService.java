package ua.com.social.demo.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    public boolean saveFile(MultipartFile file) throws IOException;
}

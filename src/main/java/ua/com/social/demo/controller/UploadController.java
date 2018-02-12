package ua.com.social.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@RestController
public class PhotoUploadController {
    @PostMapping("/api/upload")
        public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {
            if (uploadfile.isEmpty()) {
                return new ResponseEntity("please select a file!", HttpStatus.OK);
            }
            try {
                saveUploadedFiles(uploadfile);
            } catch (IOException e) {
                System.out.println(e.getCause());

            }
            return new ResponseEntity("Successfully uploaded - " +
                    uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
        }
        private  void saveUploadedFiles(MultipartFile file) throws IOException {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(System.getProperty("user.dir")+"\\src\\main\\resources\\uploaded\\photo\\"+ file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            }

        }




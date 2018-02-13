package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.service.api.GraphicService;
import ua.com.social.demo.service.api.StorageService;

import java.io.File;
import java.io.IOException;


@RestController
public class UploadController {
    @Autowired
    private GraphicService graphicService;
    @Autowired
    private StorageService storageService;
    private static final Logger LOG = Logger.getLogger(UploadController.class);

    @PostMapping("/api/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile uploadfile) {

       if (uploadfile.getOriginalFilename().equals("")) {
            return new ResponseEntity("Please select a file!", HttpStatus.CONFLICT);
        } else if (uploadfile.getOriginalFilename().endsWith("jpg") || uploadfile.getOriginalFilename().endsWith("png") || uploadfile.getOriginalFilename().endsWith("jpeg")) {
            try {
                storageService.saveFile(uploadfile);
                graphicService.resizeImgAndSave(uploadfile);
            } catch (IOException e) {
                LOG.error("Problem while upload file saved: " + e.getMessage());
                return new ResponseEntity("Error while data save", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Successfully uploaded - " +
                    uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Selected incopatible file type", HttpStatus.CONFLICT);
        }
    }


}




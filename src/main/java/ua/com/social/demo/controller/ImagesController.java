package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.service.api.StorageService;

import java.io.IOException;

@RestController
public class ImagesController {
    @Autowired
    private StorageService storageService;

    private static final Logger LOG = Logger.getLogger(ImagesController.class);

    @RequestMapping(value = "api/profile/{id}/image/", method = RequestMethod.GET)
    public ResponseEntity downloadPhoto(@PathVariable("id") Integer id, @RequestParam(value = "photoName", required = true) String photoName) {
        try {
            InputStreamResource resource = storageService.prepareFileForDownload(id, photoName);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/api/profile/{id}/image/upload")
    public ResponseEntity uploadPhoto(@RequestParam(value = "file", required = true) MultipartFile uploadfile, @PathVariable("id") Integer id) {
        String pathToPhoto = "\\" + id + "\\images\\";
        if (uploadfile.getOriginalFilename().equals("")) {
            return new ResponseEntity("Please select a file!", HttpStatus.CONFLICT);
        } else if (uploadfile.getOriginalFilename().endsWith("jpg") || uploadfile.getOriginalFilename().endsWith("png") || uploadfile.getOriginalFilename().endsWith("jpeg")) {
            try {
                storageService.saveFile(uploadfile, pathToPhoto);
                storageService.savePreview(uploadfile, pathToPhoto);
            } catch (IOException e) {
                LOG.error("Problem while upload file saved: " + e.getMessage());
                return new ResponseEntity("Error while data save", HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                LOG.error("Problem while upload file saved: " + e.getMessage());
                return new ResponseEntity("Error while data save", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Successfully uploaded - " +
                    uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Selected incopatible file type", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/api/profile/{id}/images/previews")//TODO
    public ResponseEntity getPreviews(@PathVariable("id") Integer id) {
        String path = "\\" + id + "\\images";
        storageService.getFilesNamesFromDir(path);

        return ResponseEntity.ok().build();
    }

}

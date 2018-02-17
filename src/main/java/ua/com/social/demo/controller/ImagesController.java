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
import java.util.Map;

@RestController
@RequestMapping("api/profile/{id}/image/")
public class ImagesController {
    private static final Logger LOG = Logger.getLogger(ImagesController.class);
    @Autowired
    private StorageService storageService;

    @GetMapping
    public ResponseEntity downloadPhoto(@PathVariable("id") Integer id, @RequestParam(value = "photoName") String photoName) {
        try {
            String path="\\"+id+"\\images\\"+photoName;
            InputStreamResource resource = storageService.prepareFileForDownload(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to download photo. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to download photo.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadPhoto(@RequestParam(value = "file") MultipartFile uploadfile, @PathVariable("id") Integer id) {
        String pathToPhoto = "\\" + id + "\\images\\";
        if (uploadfile.getOriginalFilename().equals("")) {
            return new ResponseEntity("Please select a file!", HttpStatus.NO_CONTENT);
        } else if (uploadfile.getOriginalFilename().endsWith("jpg") || uploadfile.getOriginalFilename().endsWith("png") || uploadfile.getOriginalFilename().endsWith("jpeg")) {
            try {
                storageService.saveFile(uploadfile, pathToPhoto);
                storageService.savePreview(uploadfile, pathToPhoto);
            } catch (IOException e) {
                LOG.error("Unsuccessful attempt to save data. " + e.getMessage());
                return new ResponseEntity("Unsuccessful attempt to save data.", HttpStatus.BAD_REQUEST);
            } catch (Exception e) {
                LOG.error("Unsuccessful attempt to save data. "+ e.getMessage());
                return new ResponseEntity("Unsuccessful attempt to save data.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity("Successfully uploaded - " +
                    uploadfile.getOriginalFilename() + ".", new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity("Selected incompatible file type.", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/previews")
    public ResponseEntity getPreviews(@PathVariable("id") Integer id) {
        try {
            String path = "\\" + id + "\\images\\previews";
            Map<String, String> previewsMap = storageService.downloadPreviews(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity(previewsMap, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to download previews. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to download previews.", HttpStatus.BAD_REQUEST);
        }

    }
}

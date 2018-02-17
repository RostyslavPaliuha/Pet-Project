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

@RestController
@RequestMapping("api/profile/{id}/video")
public class VideoController {
    private static final Logger LOG = Logger.getLogger(VideoController.class);
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity saveVideo(@PathVariable("id") Integer id, @RequestParam("video") MultipartFile file) {
        try {
            String path = "\\" + id + "\\videos\\ ";
            storageService.saveFile(file, path);
            return new ResponseEntity("Video successful video", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to store video. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to upload video.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity downloadVideo(@PathVariable("id") Integer id, @RequestParam(value = "videoName") String videoName) {
        try {
            String path = "\\" + id + "\\videos\\" + videoName;
            InputStreamResource resource = storageService.prepareFileForDownload(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("video/mp4"));
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to download video. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to download video.", HttpStatus.BAD_REQUEST);
        }
    }
}

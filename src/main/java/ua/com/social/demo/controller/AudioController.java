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
@RequestMapping("api/profile/{id}/audio")
public class AudioController {
    private static final Logger LOG = Logger.getLogger(AudioController.class);
    @Autowired
    private StorageService storageService;

    @PostMapping
    public ResponseEntity saveTrack(@RequestParam("id") Integer id, @RequestParam("audio") MultipartFile file) {
        try {
            String path = "\\" + id + "audios";
            storageService.saveFile(file, path);
            return new ResponseEntity("Track successful uploaded", HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to store track. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to upload track.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity downloadAudio(@PathVariable("id") Integer id, @RequestParam(value = "audioName") String audioName) {
        try {
            String path = "\\" + id + "\\audios\\" + audioName;
            InputStreamResource resource = storageService.prepareFileForDownload(path);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
            return new ResponseEntity(resource, headers, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to download audio. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to download audio.", HttpStatus.BAD_REQUEST);
        }
    }
}

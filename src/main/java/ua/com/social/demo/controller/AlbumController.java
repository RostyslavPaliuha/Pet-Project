/*
package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.service.api.PhotosService;

import java.util.List;
import java.util.Optional;

@RestController
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private PhotosService photosService;

    @PostMapping("api/profile/{id}/albums/create-album")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity create(@PathVariable("id") Integer profileId, @RequestBody Wall wall) {

        Optional<Integer> newlyAlbumId = albumService.createAlbum(wall);
        if (newlyAlbumId.isPresent()) {
            return ResponseEntity.status(201).body(newlyAlbumId.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("api/profile/{id}/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<> getAlbumsForCertainProfile(@PathVariable("id") Integer profileId) {
        return albumService.getAllAlbums(profileId);
    }

    @GetMapping("api/profile/{profileId}/album/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getAllPhotosForCertainAlbum(@PathVariable("albumId") Integer albumId) {
        return photosService.getAllfromAlbum(albumId);
    }

    @PostMapping("api/profile/{id}/album/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoto(@PathVariable("id") Integer profileId, @PathVariable("id") Integer albumId, @RequestBody Post post) {
        post.setAlbumId(albumId);
        photosService.createPhoto(post);
    }
}
*/

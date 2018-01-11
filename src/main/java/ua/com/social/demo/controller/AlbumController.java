package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.service.AlbumService;
import ua.com.social.demo.service.PhotosService;

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
    public ResponseEntity create(@PathVariable("id") Integer profileId, @RequestBody Album album) {
        album.setProfileId(profileId);
        Optional<Integer> newlyAlbumId = albumService.createAlbum(album);
        if (newlyAlbumId.isPresent()) {
            return ResponseEntity.status(201).body(newlyAlbumId.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("api/profile/{id}/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAlbumsForCertainProfile(@PathVariable("id") Integer profileId) {
        return albumService.getAllAlbums(profileId);
    }

    @GetMapping("api/profile/{profileId}/album/{albumId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Photo> getAllPhotosForCertainAlbum(@PathVariable("albumId") Integer albumId) {
        return photosService.getAllfromAlbum(albumId);
    }

    @PostMapping("api/profile/{id}/album/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoto(@PathVariable("id") Integer profileId, @PathVariable("id") Integer albumId, @RequestBody Photo photo) {
        photo.setAlbumId(albumId);
        photosService.createPhoto(photo);
    }
}

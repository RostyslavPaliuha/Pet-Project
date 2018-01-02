package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.impl.PhotosRepository;
import ua.com.social.demo.service.impl.AlbumServiceImpl;

import java.util.List;

@RestController
public class AlbumController {
    @Autowired
    private AlbumServiceImpl albumService;
    @Autowired
    private PhotosRepository photosRepository;

    @PostMapping("api/profile/{id}/albums/create-album")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("id") Integer profileId, @RequestBody Album album) {
        album.setProfileId(profileId);
        albumService.createAlbum(album);
    }

    @GetMapping("api/profile/{id}/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAlbumsForCertainProfile(@PathVariable("id") Integer profileId) {
        return albumService.getAllAlbums(profileId);
    }

    @GetMapping("api/profile/{profileId}/album/{albumId}")
    public List<Photo> getAllPhotosForCertainAlbum(@PathVariable("albumId") Integer albumId) {
        return photosRepository.getAllfromAlbum(albumId);
    }

    @PostMapping("api/profile/{id}/album/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoto(@PathVariable("id") Integer profileId, @PathVariable("id") Integer albumId,@RequestBody Photo photo) {
       photo.setAlbumId(albumId);
        photosRepository.persist(photo);
    }
}

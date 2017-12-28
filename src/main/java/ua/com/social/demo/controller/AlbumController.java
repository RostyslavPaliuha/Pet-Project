package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("profile/{id}/albums")
    @ResponseStatus(HttpStatus.OK)
    public List<Album> getAlbumsForCertainProfile(@PathVariable("id") Integer profileId) {
        return albumService.getAllAlbums(profileId);
    }

    @GetMapping("profile/{profileId}/album/{albumId}")
    public List<Photo> getAllPhotosForCertainAlbum(@PathVariable("albumId") Integer albumId) {
        return photosRepository.getAllfromAlbum(albumId);
    }

}

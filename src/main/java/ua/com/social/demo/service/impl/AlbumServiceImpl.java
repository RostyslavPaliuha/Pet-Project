package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.impl.AlbumRepository;
import ua.com.social.demo.repository.impl.PhotosRepository;

import java.util.List;

@Service
public class AlbumServiceImpl {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotosRepository photosRepository;

    public int createAlbum(Album album) {
        return albumRepository.persistAndRetrieveId(album);
    }

    public List<Album> getAllAlbums(Integer profileId) {
        return albumRepository.getAll(profileId);
    }

    public List<Photo> getPhotosFromAlbum(Integer albumId) {
        return photosRepository.getAllfromAlbum(albumId);
    }
}

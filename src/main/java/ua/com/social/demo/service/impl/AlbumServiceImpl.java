package ua.com.social.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.impl.AlbumRepository;
import ua.com.social.demo.repository.impl.PhotosRepository;
import ua.com.social.demo.service.AlbumService;

import java.util.List;

@Service("albumService")
public class AlbumServiceImpl  implements AlbumService{
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotosRepository photosRepository;

    public Integer createAlbum(Album album) {
        return albumRepository.persistAndRetrieveId(album);
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public List<Album> getAllAlbums(Integer profileId) {
        return albumRepository.getAll(profileId);
    }

    @Override
    public boolean update(Album album) {
        return false;
    }

    public List<Photo> getPhotosFromAlbum(Integer albumId) {
        return photosRepository.getAllfromAlbum(albumId);
    }
}

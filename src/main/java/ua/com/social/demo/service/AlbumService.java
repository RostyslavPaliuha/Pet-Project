package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;

import java.util.List;
import java.util.Optional;


public interface AlbumService {
    public Optional<Integer> createAlbum(Album album);

    public boolean delete(Integer id);

    public List<Album> getAllAlbums(Integer profileId);

    public boolean update(Album album);

    public List<Photo> getPhotosFromAlbum(Integer albumId);
}

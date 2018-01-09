package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;

import java.util.List;


public interface AlbumService {
    public Integer createAlbum(Album album);

    public boolean delete(Integer id);

    public List<Album> getAllAlbums(Integer profileId);

    public boolean update(Album album);
    public List<Photo> getPhotosFromAlbum(Integer albumId);
}

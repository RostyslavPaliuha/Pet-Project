package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Photo;

import java.util.List;

public interface PhotosService {
    public boolean createPhoto(Photo photo);

    public boolean deletePhoto(Integer id);

    public Photo get(Integer id);

    public boolean update(Photo photo);

    List<Photo> getAllfromAlbum(Integer albumId);
}

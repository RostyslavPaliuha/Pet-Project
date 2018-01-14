package ua.com.social.demo.service;

import ua.com.social.demo.entity.impl.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotosService {
    public boolean createPhoto(Photo photo);

    public boolean deletePhoto(Integer photoId);

    public Optional<Photo> get(Integer photoId);

    public boolean update(Photo photo);

    List<Photo> getAllfromAlbum(Integer albumId);
}

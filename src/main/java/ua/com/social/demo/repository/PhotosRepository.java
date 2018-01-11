package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Photo;

import java.util.List;

public interface PhotosRepository {
    void persist(Photo photo);

    void delete(Integer photoId);

    Photo get(Integer id) throws EmptyResultDataAccessException;

    List<Photo> getAllFromAlbum(Integer albumId);

    void update(Photo photo);
}

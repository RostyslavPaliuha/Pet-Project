package ua.com.social.demo.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.social.demo.entity.impl.Album;

import java.util.List;

public interface AlbumRepository {
    void persist(Album album);

    void delete(Integer albumId);

    Album get(Integer albumId) throws EmptyResultDataAccessException;

    Integer persistAndRetrieveId(Album album);

    List<Album> getAll(Integer profileId);

    void updateAlbumName(Album album);
}

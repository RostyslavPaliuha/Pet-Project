package ua.com.social.demo.repository.api;


import ua.com.social.demo.entity.impl.Album;

import java.util.List;

public interface AlbumRepository extends EntityRepository<Album> {

    List<Album> readAll(Integer profileId) throws Exception;

    void updateAlbumName(Album album) throws Exception;
}

package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Photo;

import java.util.List;

public interface PhotosRepository extends EntityRepository<Photo> {

    List<Photo> getAllFromAlbum(Integer albumId) throws Exception;

}

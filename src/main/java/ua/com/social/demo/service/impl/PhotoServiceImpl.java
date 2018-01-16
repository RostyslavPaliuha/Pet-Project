package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.api.PhotosRepository;
import ua.com.social.demo.service.PhotosService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("photoService")
public class PhotoServiceImpl implements PhotosService {
    private static final Logger LOG = Logger.getLogger(PhotoServiceImpl.class);
    @Autowired
    private PhotosRepository photosRepository;

    @Override
    public boolean createPhoto(Photo photo) {
        try {
            photosRepository.create(photo);
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving photo" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deletePhoto(Integer photoId) {
        try {
            photosRepository.delete(photoId);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting photo" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Optional<Photo> get(Integer photoId) {
        Optional<Photo> photoOptional = Optional.empty();
        try {
            return Optional.ofNullable(photosRepository.read(photoId));
        } catch (Exception e) {
            LOG.error("Error while getting photo" + e.getMessage(), e);
            return photoOptional;
        }
    }

    @Override
    public boolean update(Photo photo) {
        try {
            photosRepository.update(photo);
            return true;
        } catch (Exception e) {
            LOG.error("Error while updating photo" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Photo> getAllfromAlbum(Integer albumId) {
        try {
            return photosRepository.getAllFromAlbum(albumId);
        } catch (Exception e) {
            LOG.error("Error while geting data" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}

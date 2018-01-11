package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.service.PhotosService;

import java.util.List;

@Service
public class PhotoService implements PhotosService {
    private static final Logger LOG = Logger.getLogger(AccountServiceImpl.class);
    @Autowired
    private ua.com.social.demo.repository.PhotosRepository photosRepository;


    @Override
    public boolean createPhoto(Photo photo) {
        try {
            photosRepository.persist(photo);
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving data" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deletePhoto(Integer id) {
        return false;
    }

    @Override
    public Photo get(Integer id) {
        return null;
    }

    @Override
    public boolean update(Photo photo) {
        return false;
    }

    @Override
    public List<Photo> getAllfromAlbum(Integer albumId) {
        try {
            return photosRepository.getAllFromAlbum(albumId);
        } catch (Exception e) {
            LOG.error("Error while geting data" + e.getMessage(), e);
            return null;
        }
    }
}

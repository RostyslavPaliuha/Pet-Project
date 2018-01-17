package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.api.AlbumRepository;
import ua.com.social.demo.repository.api.PhotosRepository;
import ua.com.social.demo.service.api.AlbumService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    private static final Logger LOG = Logger.getLogger(AlbumServiceImpl.class);
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private PhotosRepository photosRepository;

    public Optional<Integer> createAlbum(Album album) {
        Optional<Integer> integerOptional = Optional.empty();
        try {
            integerOptional = Optional.ofNullable(albumRepository.create(album));
            return integerOptional;
        } catch (Exception e) {
            LOG.error("Error while creating album" + e.getMessage(), e);
            return integerOptional;
        }
    }

    @Override
    public boolean delete(Integer id) {
        try {
            albumRepository.delete(id);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting album" + e.getMessage(), e);
            return false;
        }
    }

    public List<Album> getAllAlbums(Integer profileId) {
        try {
            return albumRepository.readAll(profileId);
        } catch (Exception e) {
            LOG.error("Error while getting list of albums" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(Album album) {
        try {
            albumRepository.updateAlbumName(album);
            return true;
        } catch (Exception e) {
            LOG.error("Error while updating album name" + e.getMessage(), e);
            return false;
        }
    }

    public List<Photo> getPhotosFromAlbum(Integer albumId) {
        try {
            return photosRepository.getAllFromAlbum(albumId);
        } catch (Exception e) {
            LOG.error("Error while getting photo from album" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}

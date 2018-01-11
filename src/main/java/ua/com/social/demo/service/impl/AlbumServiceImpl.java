package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.impl.AlbumRepository;
import ua.com.social.demo.repository.impl.PhotosRepository;
import ua.com.social.demo.service.AlbumService;

import java.util.List;

@Service("albumService")
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotosRepository photosRepository;

    private static final Logger LOG = Logger.getLogger(AlbumServiceImpl.class);

    public Integer createAlbum(Album album) {
        try {
            return albumRepository.persistAndRetrieveId(album);
        } catch (Exception e) {
            LOG.error("Error while creating album" + e.getMessage(), e);
            return null;
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
            return albumRepository.getAll(profileId);
        } catch (Exception e) {
            LOG.error("Error while getting list of albums" + e.getMessage(), e);
            return null;
        }
    }

    @Override
    public boolean update(Album album) {

        return false;
    }

    public List<Photo> getPhotosFromAlbum(Integer albumId) {
        try {
            return photosRepository.getAllfromAlbum(albumId);
        } catch (Exception e) {
            LOG.error("Error while getting photo from album" + e.getMessage(), e);
            return null;
        }
    }
}

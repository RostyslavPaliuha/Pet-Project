package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.PhotosRepository;
import ua.com.social.demo.repository.rowMapper.PhotosRowMapper;

import java.util.List;

@Repository("photoRepository")
public class PhotosRepositoryImpl implements PhotosRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Photo photo) {
        Object[] params = new Object[]{photo.getPhotoData(), photo.getAlbumId(), photo.getPhotoName(), photo.getPhotoDescription(), photo.getAvatar()};
        jdbcOperations.update("INSERT INTO photo(photo_data, album_id, photo_name, description, avatar) VALUES (?,?,?,?,?);", params);
    }

    @Override
    public void delete(Integer photoId) {
        jdbcOperations.update("DELETE FROM photo WHERE photo_id =?", photoId);
    }

    @Override
    public Photo get(Integer photoId) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM photo WHERE photo_id= ?", new Integer[]{photoId}, new PhotosRowMapper());
    }

    @Override
    public List<Photo> getAllFromAlbum(Integer albumId) {
        return jdbcOperations.query("SELECT * FROM photo WHERE album_id= ?", new Integer[]{albumId}, new PhotosRowMapper());
    }

    @Override
    public void update(Photo photo) {
        jdbcOperations.update("UPDATE photo SET photo_name=?,description=?,avatar=?", new Object[]{photo.getPhotoName(), photo.getPhotoDescription(), photo.getAvatar()});
    }
}

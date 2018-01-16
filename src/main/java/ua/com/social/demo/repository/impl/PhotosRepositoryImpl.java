package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.PhotosRepository;
import ua.com.social.demo.repository.rowMapper.PhotosRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("photoRepository")
public class PhotosRepositoryImpl extends AbstractRepository<Photo> implements PhotosRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(Photo photo) {
        Object[] params = new Object[]{photo.getPhotoData(), photo.getAlbumId(), photo.getPhotoName(), photo.getPhotoDescription(), photo.getAvatar()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO photo(photo_data, album_id, photo_name, description, avatar) VALUES (?,?,?,?,?);", new String[]{"photo_id"});
                    ps.setString(1, (String) params[0]);
                    ps.setInt(2, (int) params[1]);
                    ps.setString(3, (String) params[2]);
                    ps.setString(4, (String) params[3]);
                    ps.setInt(5, (int) params[4]);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(Integer photoId) {
        jdbcOperations.update("DELETE FROM photo WHERE photo_id =?", photoId);
    }

    @Override
    public Photo read(Integer photoId) throws EmptyResultDataAccessException {
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

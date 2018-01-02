package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.EntityRepository;
import ua.com.social.demo.repository.rowMapper.PhotosRowMapper;

import java.sql.Types;
import java.util.List;
@Repository
public class PhotosRepository implements EntityRepository<Photo> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Photo photo) {
        Object[] params = new Object[]{photo.getPhotoData(),photo.getAlbumId(),photo.getPhotoName(),photo.getPhotoDescription(),  photo.getAvatar()};

        jdbcOperations.update("INSERT INTO photo(photo_data, album_id, photo_name, description, avatar) VALUES (?,?,?,?,?);", params);
    }

    @Override
    public void delete(Photo photo) {
        jdbcOperations.update("DELETE FROM photo WHERE photo_id =" + photo.getPhotoId() + ";");
    }

    @Override
    public Photo get(Integer id) {
        return jdbcOperations.queryForObject("SELECT * FROM photo WHERE photo_id= ?", new Object[]{id}, new PhotosRowMapper());
    }

    public List<Photo> getAllfromAlbum(Integer albumId) {
        return jdbcOperations.query("SELECT * FROM photo WHERE album_id= ?", new Object[]{albumId}, new PhotosRowMapper());
    }
}

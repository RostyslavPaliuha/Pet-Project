package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Photo;

import com.mysql.cj.jdbc.Blob;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotosRowMapper implements RowMapper <Photo>{
    @Override
    public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Photo photo = new Photo();
        photo.setPhotoId(rs.getInt("photo_id"));
        photo.setPhotoData((Blob) rs.getBlob("photo_data"));
        photo.setAlbumId(rs.getInt("album_id"));
        photo.setAvatar(rs.getInt("profile_id"));
        return photo;
    }
}

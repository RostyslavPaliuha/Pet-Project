package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.AlbumRepository;
import ua.com.social.demo.repository.rowMapper.AlbumRowMapper;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("albumRepository")
public class AlbumRepositoryImpl extends AbstractRepository<Album> implements AlbumRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(Album album) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO album(album_name,profile_id) VALUES (?,?);", new String[]{"album_id"});
                    ps.setString(1, album.getAlbumName());
                    ps.setInt(2, album.getProfileId());
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Album read(Integer id) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM album WHERE album_id= ?", new Object[]{id}, new AlbumRowMapper());
    }

    @Override
    public void updateAlbumName(Album album) {
        jdbcOperations.update("UPDATE album SET album_name=? WHERE profile_id=? AND album_id=?", new Object[]{album.getAlbumName(), album.getProfileId(), album.getAlbumId()});
    }

    @Override
    public void delete(Integer albumId) {
        jdbcOperations.update("DELETE FROM album WHERE album_id =?", new Object[]{albumId});
    }

    @Override
    public List<Album> readAll(Integer profileId) {
        return jdbcOperations.query("SELECT * FROM album WHERE profile_id=?", new Object[]{profileId}, new AlbumRowMapper());
    }
}

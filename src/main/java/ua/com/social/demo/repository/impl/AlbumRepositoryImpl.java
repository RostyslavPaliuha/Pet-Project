package ua.com.social.demo.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.repository.AlbumRepository;
import ua.com.social.demo.repository.rowMapper.AlbumRowMapper;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;

@Repository("albumRepository")
public class AlbumRepositoryImpl implements AlbumRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public void persist(Album album) {
        Object[] params = new Object[]{album.getAlbumName(), album.getProfileId()};
        int[] types = new int[]{Types.VARCHAR, Types.INTEGER};
        jdbcOperations.update("INSERT INTO album(album_name,profile_id) VALUES (?,?);", params, types);
    }

    @Override
    public void delete(Integer albumId) {
        jdbcOperations.update("DELETE FROM album WHERE album_id =?", new Object[]{albumId});
    }

    @Override
    public Album get(Integer id) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM album WHERE album_id= ?", new Object[]{id}, new AlbumRowMapper());
    }

    @Override
    public Integer persistAndRetrieveId(Album album) {
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
    public List<Album> getAll(Integer profileId) {
        return jdbcOperations.query("SELECT * FROM album WHERE profile_id=?", new Object[]{profileId}, new AlbumRowMapper());
    }

    @Override
    public void updateAlbumName(Album album) {
        jdbcOperations.update("UPDATE album SET album_name=? WHERE profile_id=? AND album_id=?", new Object[]{album.getAlbumName(), album.getProfileId(), album.getAlbumId()});
    }
}

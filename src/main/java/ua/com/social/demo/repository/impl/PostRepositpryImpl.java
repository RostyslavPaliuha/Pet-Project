package ua.com.social.demo.repository.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.repository.api.AbstractRepository;
import ua.com.social.demo.repository.api.PostRepository;
import ua.com.social.demo.repository.rowMapper.PostRowMapper;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("photoRepository")
public class PostRepositpryImpl extends AbstractRepository<Post> implements PostRepository {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Override
    public Integer create(Post post) {
        Object[] params = new Object[]{post.getPostDate(),post.getWallId(),post.getPostContent(),post.getAudio(),post.getPhoto(),post.getVideo(),post.getPostCreaterId()};
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(connection -> {
                    PreparedStatement ps = connection.prepareStatement("INSERT INTO post(post_date, wall_id, post_content, audio, video, photo, creater_id) VALUES (?,?,?,?,?,?,?);", new String[]{"post_id"});
                    ps.setTimestamp(1, Timestamp.valueOf((LocalDateTime)params[0]));
                    ps.setInt(2, (int) params[1]);
                    ps.setString(3, (String) params[2]);
                    ps.setString(4, (String) params[3]);
                    ps.setString(5, (String) params[4]);
                    ps.setString(6, (String) params[5]);
                    ps.setInt(7, (int) params[6]);
                    return ps;
                },
                keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void delete(Integer postId) {
        jdbcOperations.update("DELETE FROM post WHERE post_id =?", postId);
    }

    @Override
    public Post read(Integer postId) throws EmptyResultDataAccessException {
        return jdbcOperations.queryForObject("SELECT * FROM post WHERE post_id= ?", new Integer[]{postId}, new PostRowMapper());
    }

    @Override
    public List<Post> getAllFromWall(Integer wallId) {
        return jdbcOperations.query("SELECT * FROM post WHERE wall_id= ?", new Integer[]{wallId}, new PostRowMapper());
    }

    @Override
    public void update(Post post) {
        //jdbcOperations.update("UPDATE photo SET photo_name=?,description=?,avatar=?", new Object[]{post.getPhotoName(), post.getPhotoDescription(), post.getAvatar()});
    }
}

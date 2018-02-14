package ua.com.social.demo.repository.rowMapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.social.demo.entity.impl.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setPostDate(rs.getTimestamp("post_date").toLocalDateTime());
        post.setWallId(rs.getInt("wall_id"));
        post.setPostContent(rs.getString("post_content"));
        post.setAudio(rs.getString("audio"));
        post.setVideo(rs.getString("video"));
        post.setPhoto(rs.getString("photo"));
        post.setPostCreaterId(rs.getInt("creater_id"));
        return post;
    }
}

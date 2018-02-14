package ua.com.social.demo.service.api;

import ua.com.social.demo.entity.impl.Post;
import java.util.List;

public interface PostService {
    public boolean createPost(Post post);

    public boolean deletePost(Integer postId);

    public boolean update(Post post);

    List<Post> getAllfromWall(Integer wallId);
}

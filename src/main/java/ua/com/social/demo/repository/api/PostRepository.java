package ua.com.social.demo.repository.api;

import ua.com.social.demo.entity.impl.Post;

import java.util.List;

public interface PostRepository extends EntityRepository<Post> {

    List<Post> getAllFromWall(Integer wallId) throws Exception;

}

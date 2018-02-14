package ua.com.social.demo.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.repository.api.PostRepository;
import ua.com.social.demo.service.api.PostService;

import java.util.Collections;
import java.util.List;

@Service("postService")
public class PostServiceImpl implements PostService {
    private static final Logger LOG = Logger.getLogger(PostServiceImpl.class);
    @Autowired
    private PostRepository postRepository;

    @Override
    public boolean createPost(Post post) {
        try {
            postRepository.create(post);
            return true;
        } catch (Exception e) {
            LOG.error("Error while saving post" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean deletePost(Integer postId) {
        try {
            postRepository.delete(postId);
            return true;
        } catch (Exception e) {
            LOG.error("Error while deleting post" + e.getMessage(), e);
            return false;
        }
    }


    @Override
    public boolean update(Post post) {
        try {
            postRepository.update(post);
            return true;
        } catch (Exception e) {
            LOG.error("Error while updating post" + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Post> getAllfromWall(Integer wallId) {
        try {
            return postRepository.getAllFromWall(wallId);
        } catch (Exception e) {
            LOG.error("Error while getting posts" + e.getMessage(), e);
            return Collections.emptyList();
        }
    }
}

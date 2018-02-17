package ua.com.social.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.service.api.PostService;

import java.util.List;


@RestController
@RequestMapping("api/profile/{id}/wall")
public class PostController {
    private static final Logger LOG = Logger.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity getPosts(@RequestParam("id") Integer id) {
        try {
            List posts = postService.getAllfromWall(id);
            return new ResponseEntity(posts, HttpStatus.OK);
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt get posts." + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt get posts.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/create-post")
    public ResponseEntity createNewPost(@RequestBody Post post) {
        try {
            postService.createPost(post);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt create new post. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt create new post.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/delete-post/{id}")
    public ResponseEntity deletePost(@RequestParam("id") Integer id) {
        try {
            postService.deletePost(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to delete post. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to delete post.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-post")
    public ResponseEntity updatePost(@RequestBody Post post) {
        try {
            postService.update(post);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            LOG.error("Unsuccessful attempt to update post. " + e.getMessage());
            return new ResponseEntity("Unsuccessful attempt to update post.", HttpStatus.BAD_REQUEST);
        }
    }
}

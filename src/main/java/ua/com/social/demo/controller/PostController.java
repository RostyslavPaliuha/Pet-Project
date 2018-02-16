package ua.com.social.demo.controller;

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
    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity getPosts(@RequestParam("id") Integer id) {
        List posts = postService.getAllfromWall(id);
        return new ResponseEntity(posts, HttpStatus.OK);
    }

    @PostMapping("/create-post")
    public ResponseEntity createNewPost(@RequestBody Post post) {
        postService.createPost(post);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete-post/{id}")
    public ResponseEntity deletePost(@RequestParam("id") Integer id) {
        postService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-post")
    public ResponseEntity updatePost(@RequestBody Post post) {
        postService.update(post);
        return ResponseEntity.ok().build();
    }
}

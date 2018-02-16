package ua.com.social.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.service.api.PostService;

import java.util.List;

@RestController
public class AlbumController {
    @Autowired
    private PostService postService;

    @GetMapping("api/profile/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> getPosts(@PathVariable("id") Integer profileId) {
        return postService.getAllfromWall(profileId);
    }

    @PostMapping("api/profile/{id}/new-post")
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhoto(@RequestBody Post post) {
        postService.createPost(post);
    }
}

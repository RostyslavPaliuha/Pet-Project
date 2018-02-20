package it.ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Post;
import ua.com.social.demo.repository.api.PostRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/create-social.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class PostRepositoryTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void persist_getAll_delete_expectException() throws Exception {
        Post testPost = new Post();
        testPost.setPostDate(LocalDateTime.of(2018, 02, 14, 3, 56));
        testPost.setWallId(1);
        testPost.setPostCreaterId(1);
        testPost.setPostContent("TEST CONTENT");
        testPost.setAudio("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\audios");
        testPost.setVideo("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\video");
        testPost.setPhoto("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\photo");
        Post testPost1 = new Post();
        testPost1.setPostDate(LocalDateTime.of(2018, 02, 14, 3, 56));
        testPost1.setWallId(1);
        testPost1.setPostCreaterId(1);
        testPost1.setPostContent("TEST CONTENT");
        testPost1.setAudio("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\audios");
        testPost1.setVideo("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\video");
        testPost1.setPhoto("D:\\development\\Java_Dev\\social\\social\\src\\main\\resources\\usersFileArchive\\10\\photo");

        Integer postId = postRepository.create(testPost);
        Integer postid1 = postRepository.create(testPost1);
        assertEquals(postId, new Integer(1));
        assertEquals(postid1, new Integer(2));
        List<Post> postsFromTestWall = postRepository.getAllFromWall(1);
        assertEquals(new Integer(postsFromTestWall.size()), new Integer(2));
        postRepository.delete(postId);
        List<Post> posts = postRepository.getAllFromWall(1);
        assertEquals(new Integer(1), new Integer(posts.size()));

    }
}

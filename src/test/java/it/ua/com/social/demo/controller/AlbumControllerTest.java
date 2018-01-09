package it.ua.com.social.demo.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.service.AlbumService;
import ua.com.social.demo.service.PhotosService;
import ua.com.social.demo.service.impl.AlbumServiceImpl;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AlbumControllerTest {

    private MockMvc mockMvc;
    @MockBean
    private AlbumServiceImpl albumService;
    @MockBean
    private PhotosService photosService;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private Filter springSecurityFilterChain;
    private List<Album> albums = Arrays.asList(new Album("TEST ALBUM", 1));
    private List<Photo> photos = Arrays.asList(new Photo("TEST PHOTO"));
    private Album album = new Album("TEST ALBUM", 1);

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).addFilters(springSecurityFilterChain).build();
        Mockito.when(albumService.createAlbum(album)).thenReturn(1);
        Mockito.when(albumService.getAllAlbums(1)).thenReturn(albums);
        Mockito.when(photosService.createPhoto(photos.get(0))).thenReturn(true);
        Mockito.when(photosService.getAllfromAlbum(1)).thenReturn(photos);

    }

    @Test
    public void login_createAlbum_reviewAlbums_createPhoto_reviewCertainAlbumPhotos() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/login").content("{\n" +
                "\t\"email\":\"pro@gmail.com\",\n" +
                "\t\"password\":\"1111\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn();
        String token = mvcResult.getResponse().getHeader("Authentication");
        mockMvc.perform(post("/api/profile/1/albums/create-album")
                .header("Authentication", token)
                .content(" {\"albumName\":\"TEST ALBUM\",\"profileId\":\"1\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(201));
        mockMvc.perform(get("/api/profile/1/albums").header("Authentication", token))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/profile/1/album/1").header("Authentication", token))
                .andExpect(status().isOk());
        mockMvc.perform(post("/api/profile/1/album/1")
                .header("Authentication", token)
                .content("{\"photoName\":\"TEST PHOTO\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().is(201));
    }
}
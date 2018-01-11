package unit.ua.com.social.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.service.AlbumService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(scripts = "classpath:sql/create-social.sql"),
        @Sql(scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class AlbumServiceImplTest {
    @Autowired
    private AlbumService albumService;
    private Album album = new Album("TEST ALBUM", 1);

    @Test
    public void createAlbum() throws Exception {
        Optional<Integer> integerOptional0 = albumService.createAlbum(album);
        Optional<Integer> integerOptional1 = albumService.createAlbum(album);
        Optional<Integer> integerOptional2 = albumService.createAlbum(album);
        Optional<Integer> integerOptional3 = albumService.createAlbum(album);
        Optional<Integer> integerOptional4 = albumService.createAlbum(album);
        List<Album> albums = albumService.getAllAlbums(1);
        assertEquals(5, albums.size());
        assertTrue(integerOptional0.isPresent());
        assertTrue(integerOptional0.isPresent());
        assertEquals(new Integer(integerOptional0.get()), new Integer(1));
        assertTrue(integerOptional1.isPresent());
        assertEquals(new Integer(integerOptional1.get()), new Integer(2));
        assertTrue(integerOptional2.isPresent());
        assertEquals(new Integer(integerOptional2.get()), new Integer(3));
        assertTrue(integerOptional3.isPresent());
        assertEquals(new Integer(integerOptional3.get()), new Integer(4));
        assertTrue(integerOptional4.isPresent());
        assertEquals(new Integer(integerOptional4.get()), new Integer(5));
    }

}
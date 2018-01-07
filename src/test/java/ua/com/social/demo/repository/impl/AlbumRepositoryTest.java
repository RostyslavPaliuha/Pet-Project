package ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.entity.impl.Album;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "classpath:sql/create-social.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})
public class AlbumRepositoryTest {
    @Autowired
    private AlbumRepository albumRepository;
    private Album testAlbum;
    private Album testAlbum2;

    public AlbumRepositoryTest() {
        this.testAlbum = new Album("testAlbum", 1);
        this.testAlbum2 = new Album("testAlbum2", 1);
    }

    @Test
    public void persist_getAll_delete() throws Exception {
        Integer testAlbumId = albumRepository.persistAndRetrieveId(testAlbum);
        Integer testAlbum2Id = albumRepository.persistAndRetrieveId(testAlbum2);
        testAlbum.setAlbumId(testAlbumId);
        testAlbum2.setAlbumId(testAlbum2Id);
        List<Album> actualTestAlbums = albumRepository.getAll(1);
        assertEquals(testAlbum.getAlbumName(), actualTestAlbums.get(0).getAlbumName());
        albumRepository.delete(testAlbum2);
        List testalbumsAfterDelete = albumRepository.getAll(1);
        assertEquals(1, testalbumsAfterDelete.size());
    }
}
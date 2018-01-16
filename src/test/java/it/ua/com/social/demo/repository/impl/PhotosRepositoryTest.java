package it.ua.com.social.demo.repository.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.entity.impl.Album;
import ua.com.social.demo.entity.impl.Photo;
import ua.com.social.demo.repository.api.AlbumRepository;
import ua.com.social.demo.repository.api.PhotosRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:test-application.properties")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/create-social.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/insertdata.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/cleardata.sql")})

public class PhotosRepositoryTest {
    @Autowired
    private PhotosRepository photosRepository;
    @Autowired
    private AlbumRepository albumRepository;
    private Album testAlbum;
    private Photo testPhoto;
    private Photo testPhoto2;

    public PhotosRepositoryTest() {
        this.testAlbum = new Album("testAlbum", 1);
        this.testPhoto = new Photo("testPhoto", "empty", "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", 1);
        this.testPhoto.setAlbumId(2);
        this.testPhoto2 = new Photo("testPhoto2", "empty", "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", 0);
        this.testPhoto2.setAlbumId(2);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void persist_getAll_delete_expectException() throws Exception {
        Integer testAlbumId = albumRepository.create(testAlbum);
        testPhoto.setAlbumId(testAlbumId);
        testPhoto2.setAlbumId(testAlbumId);
        photosRepository.create(testPhoto);
        photosRepository.create(testPhoto2);
        List<Photo> photosFromTestAlbum = photosRepository.getAllFromAlbum(2);
        assertEquals(new Integer(2), new Integer(photosFromTestAlbum.size()));
        assertEquals(testPhoto.getPhotoName(), photosFromTestAlbum.get(0).getPhotoName());
        assertEquals(testPhoto.getPhotoDescription(), photosFromTestAlbum.get(0).getPhotoDescription());
        assertEquals(testPhoto.getPhotoData(), photosFromTestAlbum.get(0).getPhotoData());
        assertEquals(testPhoto.getAvatar(), photosFromTestAlbum.get(0).getAvatar());
        testPhoto2.setPhotoId(2);
        photosRepository.delete(testPhoto2.getPhotoId());
        List<Photo> photos = photosRepository.getAllFromAlbum(testAlbumId);
        assertEquals(new Integer(1), new Integer(photos.size()));
        photosRepository.read(2);
    }
}

package unit.ua.com.social.demo.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.service.api.StorageService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StorageServiceImplTest {
    @Autowired
    private StorageService storageService;
    private Path testFilePath;
    private MultipartFile multipartFile;
    private String path = "\\"+Long.MAX_VALUE + "\\testDir\\";
    private Path testDestinationPath = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive\\" + path);
    private Path mainPath = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive\\"+Long.MAX_VALUE+"\\");

    public StorageServiceImplTest() {
        try {
            testFilePath = Paths.get(System.getProperty("user.dir") + "\\src\\test\\resources\\test.txt");
            byte[] bytes = Files.readAllBytes(testFilePath);
            multipartFile = new MockMultipartFile("file", testFilePath.getFileName().toString(), "text", bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
    }

    @Test
    public void saveFile() throws Exception {
        Files.createDirectories(testDestinationPath);
        storageService.saveFile(multipartFile, path);
        assertTrue(Files.exists(testDestinationPath));
        Files.delete(testDestinationPath);
    }

    @Test
    public void mkDirForNewUser() throws Exception {
        storageService.mkDirForNewUser(Integer.valueOf(String.valueOf(Long.MAX_VALUE)));
    assertTrue(Files.exists(mainPath));
    Files.delete(mainPath);
    }

    @Test
    public void checkMainPath() throws Exception {
    }

    @Test
    public void createMainPath() throws Exception {
    }

}
package it.ua.com.social.demo.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.service.api.StorageService;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ImageControllerTest extends LoginControllerTest {
    @MockBean
    private StorageService storageService;

    @Test
    public void uploadFile() throws Exception {
        login();
        String token = getHeader();
        byte[] bytes = Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive\\defaults\\Question-mark-face.jpg"));
        String fileName = Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\usersFileArchive\\defaults\\Question-mark-face.jpg").getFileName().toString();
        MockMultipartFile file = new MockMultipartFile("file", fileName, "image", bytes);
        Mockito.when(storageService.saveFile(file, "path")).thenReturn(true);
        mockMvc.perform(fileUpload("/api/profile/1/image/upload")
                .file(file)
                .header("Authentication", token))
                .andExpect(status().isOk());

    }
}
package unit.ua.com.social.demo.controller;

import it.ua.com.social.demo.controller.LoginControllerTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ua.com.social.demo.DemoApplication;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PhotoUploadControllerTest extends LoginControllerTest{

    @Test
    public void uploadFile() throws Exception {
        super.login();
        byte[]bytes=Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\src\\test\\resources\\test.txt"));
        MockMultipartFile firstFile = new MockMultipartFile("test",bytes);
        getMockMvc().perform(fileUpload("/api/upload")
                .file(firstFile).contentType(MediaType.ALL)
                .header("Authentication", getHeader()))
                .andExpect(status().isOk());


    }



}
package unit.ua.com.social.demo.service;

import org.assertj.core.util.Files;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import ua.com.social.demo.DemoApplication;
import ua.com.social.demo.service.api.StorageService;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class StorageServiceImplTest {
    @Autowired
    private StorageService storageService;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void saveFile() throws Exception {
    }

    @Test
    public void mkDirForNewUser() throws Exception {
       /* storageService.mkDirForNewUser(1111);*/
        String userName = encoder.encode(new Integer(11).toString());
        System.out.println(System.getProperty("user.dir") + "\\src\\main\\resources\\userFilesArchive\\" + "userName" + "\\audio");
        new File(System.getProperty("user.dir") + "\\src\\main\\resources\\userFilesArchive\\" + userName + "\\audio").mkdirs();
       }


    @Test
    public void checkMainPath() throws Exception {
    }

    @Test
    public void createMainPath() throws Exception {
    }

}
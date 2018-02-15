package ua.com.social.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.social.demo.service.api.GraphicService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service("graphicService")
public class GraphicServiceImpl implements GraphicService {
    public GraphicServiceImpl() {
    }
    @Override
    public Map<String, List<File>> readAllProfileImg(Integer profileId) {
        return null;
    }

    @Override
    public Map<String, List<File>> readAllImagesFromDir() {
        return null;
    }

    public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


}

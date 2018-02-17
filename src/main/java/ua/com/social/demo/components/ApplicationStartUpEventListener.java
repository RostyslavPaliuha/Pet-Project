package ua.com.social.demo.components;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ua.com.social.demo.service.api.StorageService;

import java.io.IOException;

@Component
public class ApplicationStartUpEventListener implements ApplicationListener<ApplicationReadyEvent> {
    private static final Logger LOG = Logger.getLogger(ApplicationStartUpEventListener.class);
    @Autowired
    private StorageService storageService;

    /**
     * This event is executed as late as possible to indicate that
     * the application is ready to service requests.
     */
    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        try {
            if (storageService.checkMainPath()) {
                storageService.createMainPath();
            }
        } catch (IOException e) {
            LOG.error("Exception while check dir path to user`s files archive" + e.getMessage());
            e.printStackTrace();
        }
    }

}
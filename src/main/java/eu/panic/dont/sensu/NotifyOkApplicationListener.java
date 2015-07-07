package eu.panic.dont.sensu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple listener that sends out a Ok notification each time the application context is successfully refreshed or
 * initialized.
 */
@Component
public class NotifyOkApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private NotifySensuService service;

    private static Logger logger = LoggerFactory.getLogger(NotifyOkApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        String message = "Application context successfully initialised at " + LocalDateTime.now().format(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        logger.debug(message);
        service.sendMessage(Status.OK, message);
    }
}

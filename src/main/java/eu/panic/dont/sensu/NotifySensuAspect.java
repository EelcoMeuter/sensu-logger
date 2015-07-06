package eu.panic.dont.sensu;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

@Aspect
@Component
public class NotifySensuAspect {

    @Value("${sensu.app.name}")
    String name;

    @Value("${sensu.client.port}")
    int port;

    private static final Logger logger = LoggerFactory.getLogger(NotifySensuAspect.class);

    /**
     * This operation is triggered when any method that is annotated with @link{NotifySensu} throws an exception
     */
    @AfterThrowing(pointcut = "@annotation(eu.panic.dont.sensu.NotifySensu)", throwing =
            "error")
    public void notifiesSensu(Throwable error) {
        logger.debug("triggered NotifySensu aspect");

        Message message = new Message(name, error.getMessage());
        ObjectMapper mapper = new ObjectMapper();
        Socket socket;
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            String msg = "Unable to open socket to Sensu!";
            logger.error(msg);
            logger.debug(msg, e);
            return;
        }
        OutputStream out;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            String msg = "Unable to create outputstream to Sensu!";
            logger.error(msg);
            logger.debug(msg, e);
            return;
        }
        try {
            out.write(mapper.writeValueAsBytes(message));
        } catch (IOException e) {
            String msg = "Unable to write JSON!";
            logger.error(msg);
            logger.debug(msg, e);
            return;
        }
    }

}

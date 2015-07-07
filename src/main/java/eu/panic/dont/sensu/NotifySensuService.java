package eu.panic.dont.sensu;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * The service that opens the socket to Sensu and published the event
 */
@Component
public class NotifySensuService {

    private final static Logger logger = LoggerFactory.getLogger(NotifySensuService.class);

    @Value("${sensu.app.name}")
    String name;

    @Value("${sensu.client.port}")
    int port;

    public void sendMessage(Status status, String message) {
        Message msg = new Message(name, message, status);
        ObjectMapper mapper = new ObjectMapper();
        Socket socket = null;
        try {
            socket = new Socket("localhost", port);
        } catch (IOException e) {
            String errorMessage = "Unable to open socket to Sensu!";
            logger.error(errorMessage);
            logger.debug(errorMessage, e);
            return;
        }
        OutputStream out = null;
        try {
            out = socket.getOutputStream();
        } catch (IOException e) {
            String errorMessage = "Unable to create outputstream to Sensu!";
            logger.error(errorMessage);
            logger.debug(errorMessage, e);
            return;
        }
        try {
            out.write(mapper.writeValueAsBytes(msg));
        } catch (IOException e) {
            String errorMessage = "Unable to write JSON!";
            logger.error(errorMessage);
            logger.debug(errorMessage, e);
            return;
        }
    }

}

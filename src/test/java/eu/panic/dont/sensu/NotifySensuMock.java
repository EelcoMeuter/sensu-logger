package eu.panic.dont.sensu;

import org.springframework.stereotype.Component;

@Component
public class NotifySensuMock {

    public enum Message {
        EXCEPTION,
        RUNTIME_EXCEPTION
    }

    @NotifySensu
    public void nofifyException() throws Exception {
        throw new Exception(Message.EXCEPTION.name());
    }

    @NotifySensu
    public void notifyRuntimeException() {
        throw new RuntimeException(Message.RUNTIME_EXCEPTION.name());
    }

}

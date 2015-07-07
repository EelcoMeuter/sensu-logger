package eu.panic.dont.sensu;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotifySensuAspect {

    @Autowired
    NotifySensuService service;

    private static final Logger logger = LoggerFactory.getLogger(NotifySensuAspect.class);

    /**
     * This operation is triggered when any method that is annotated with @link{NotifySensu} throws an exception
     */
    @AfterThrowing(pointcut = "@annotation(NotifySensu)", throwing =
            "error")
    public void notifiesSensu(Throwable error) {
        logger.debug("triggered NotifySensu aspect");
        service.sendMessage(Status.CRITICAL, error.getMessage());
    }
}

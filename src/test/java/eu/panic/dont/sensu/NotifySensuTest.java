package eu.panic.dont.sensu;

import org.junit.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class NotifySensuTest {

    @Test(expected = RuntimeException.class)
    public void should_handle_runtime_exception() {
        NotifySensuMock notifySensuMock = new NotifySensuMock();
        AspectJProxyFactory factory = new AspectJProxyFactory(notifySensuMock);
        NotifySensuAspect aspect = new NotifySensuAspect();
        aspect.port = 3030;
        aspect.name = "junit";
        factory.addAspect(aspect);
        NotifySensuMock proxy = factory.getProxy();
        proxy.notifyRuntimeException();
    }

}

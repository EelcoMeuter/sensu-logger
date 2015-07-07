package eu.panic.dont.sensu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppTestConfig.class })
public class NotifySensuIntegrationTest {

    @Autowired
    NotifySensuAspect aspect;

    @Test
    public void should_autowire_correctly() {
        assertNotNull(aspect);
    }
}
package io.mb.dealers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DealerApplicationTest {

    @Autowired
    AnnotationConfigServletWebServerApplicationContext context;

    @Test
    void contextLoads() {
        // Validate that the Spring Boot application context starts successfully
        Assertions.assertTrue(context.isRunning());
    }
}

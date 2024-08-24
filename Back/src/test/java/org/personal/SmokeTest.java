package org.personal;

import org.personal.controllers.GastoController;
import org.junit.jupiter.api.Test;
import org.personal.services.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    // Controllers
    @Autowired
    private GastoController gastoController;

    // Services
    @Autowired
    private GastoService gastoService;

    // Test
    @Test
    public void contextLoads() throws Exception {
        // Controllers
        assertThat(gastoController).isNotNull();

        // Services
        assertThat(gastoService).isNotNull();
    }
}

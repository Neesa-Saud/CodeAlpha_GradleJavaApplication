package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
    void applicationClassCanBeCreated() {
        App app = new App();
        assertTrue(app != null);
    }
}
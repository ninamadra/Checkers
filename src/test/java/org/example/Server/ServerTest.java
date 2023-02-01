package org.example.Server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServerTest {

    @Test
    void getInstance() {
        assertNotNull(Server.getInstance());
    }
}
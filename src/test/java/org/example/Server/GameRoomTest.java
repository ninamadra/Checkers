package org.example.Server;

import org.example.Client.GUI;
import org.example.Client.Main;
import org.example.model.Color;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class GameRoomTest {

    @Test
    void getInstance() {
        assertNotNull(GameRoom.getInstance());
    }

    @Test
    void getColorToSet() {
        GameRoom gr = GameRoom.getInstance();
        assertEquals(Color.WHITE, gr.getColorToSet());
        assertEquals(Color.BLACK, gr.getColorToSet());
    }

    @Test
    void createGame() {
        GameRoom gr = GameRoom.getInstance();
        assertThrows(capacityException.class, () -> gr.createGame("THAI"));
    }
}
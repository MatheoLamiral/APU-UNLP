package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MediaPlayerTest {
    private MediaPlayer mediaPlayer;

    @BeforeEach
    void setUp() {
        this.mediaPlayer = new MediaPlayer(List.of(new Audio(), new VideoFile(), new VideoStreamAdapter(new VideoStream())));
    }

    @Test
    void play() {
        assertEquals("Reproduciendo audio ...Reproduciendo archivo de video ...Reproduciendo video stream ...", this.mediaPlayer.play());
        // hago que retorne un String para testearlo? pero no puedo modificar VideoStream, como hago?
    }
}
package org.example;

import java.util.List;

public class MediaPlayer {
    private List<Media> media;

    public MediaPlayer(List<Media> media) {
        this.media = media;
    }

    public void play(){
        this.media.stream().forEach(Media::play);
    }
}

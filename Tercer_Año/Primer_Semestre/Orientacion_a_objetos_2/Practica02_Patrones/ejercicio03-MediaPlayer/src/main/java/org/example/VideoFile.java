package org.example;

public class VideoFile implements Media {

    @Override
    public void play() {
        System.out.println("Reproduciendo archivo de video ...");
    }
}

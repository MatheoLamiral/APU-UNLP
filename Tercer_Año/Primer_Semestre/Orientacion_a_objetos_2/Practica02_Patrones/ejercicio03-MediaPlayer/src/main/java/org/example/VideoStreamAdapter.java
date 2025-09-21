package org.example;

public class VideoStreamAdapter implements Media{
    private VideoStream adaptee;

    public VideoStreamAdapter(VideoStream adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void play() {
        this.adaptee.reproduce();
    }
}

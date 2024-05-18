package com.wilson901015.lesotho_trivia_game;

import javafx.scene.media.Media;

public class MediaPlayer {
    private javafx.scene.media.MediaPlayer mediaPlayer;

    public MediaPlayer(String videoPath) {
        Media media = new Media(MediaPlayer.class.getResource(videoPath).toExternalForm());
        mediaPlayer = new javafx.scene.media.MediaPlayer(media);
    }

    public javafx.scene.media.MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play() {
        mediaPlayer.play();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void stop() {
        mediaPlayer.stop();
    }

    public boolean isPlaying() {
        return mediaPlayer.getStatus() == javafx.scene.media.MediaPlayer.Status.PLAYING;
    }

    public void seekToStart() {
        mediaPlayer.seek(mediaPlayer.getStartTime());
    }

    public void setOnEndOfMedia(Runnable action) {
        mediaPlayer.setOnEndOfMedia(action);
    }
}

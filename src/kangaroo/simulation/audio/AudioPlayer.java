package kangaroo.simulation.audio;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioPlayer {
    public static final int INFINITY = Integer.MAX_VALUE;
    
    private Media media;
    private MediaPlayer player,player1;
    private boolean isPlaying;
    private String path;

    public AudioPlayer(String path, boolean autoPlay) {
        try {
            this.path=path;
            media = new Media(getClass().getResource("/resources/audio/" + path + ".mp3").toString());
            player = new MediaPlayer(media);
            player.setAutoPlay(autoPlay);
            
        } catch (Exception e) {
        }
    }

    public void play(boolean repeated, int count) {
        isPlaying = false;
        if(player.getMedia() == null) return;
        if(repeated) player.setCycleCount(count);
        player.play();
        isPlaying = true;
    }
    
    public String getPath(){
        return path;
    }
    public void setVolume(double value) {
        player.setVolume(value);
    }
    
    public double getVolume() {
        return (double)player.getVolume();
    }

    public void stop() {
        isPlaying = false;
        player.stop();
    }

    public void close() {
        stop();
        player.dispose();
    }

    public boolean isPlaying() {
        return this.isPlaying;
    }

    public Media getMedia() {
        return media;
    }
    
    public MediaPlayer getPlayer() {
        return player;
    }
    
    public void setMute(boolean mute) {
        player.setMute(mute);
    }
}

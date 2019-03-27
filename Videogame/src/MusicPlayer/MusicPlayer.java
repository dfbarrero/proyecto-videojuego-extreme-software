/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayer;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author razvanvc
 */
public class MusicPlayer {

    /**
     *
     */
    public static Clip MainMenuMusic;
    private int lastTrackNumber = 1;
    private Clip lastTrack = null;
    private boolean trackPlaying = false;
    
    
    public MusicPlayer() {
    }
    
    public static void init (){
        
    }

    public int getLastTrackNumber() {
        return lastTrackNumber;
    }

    public void setLastTrackNumber(int lastTrackNumber) {
        this.lastTrackNumber = lastTrackNumber;
    }

    public boolean isTrackPlaying() {
        return trackPlaying;
    }

    public void setTrackPlaying(boolean trackplaying) {
        this.trackPlaying = trackplaying;
    }

    public Clip getLastTrack() {
        return lastTrack;
    }

    public void setLastTrack(Clip lastTrack) {
        this.lastTrack = lastTrack;
    }
    
    
    public void playTrack(int i){
        
        setLastTrackNumber(i);
        
        if (i != getLastTrackNumber() && isTrackPlaying()){
            getLastTrack().stop();
        }
        
        setTrackPlaying(true);
        if (i == 1) {
            
            setLastTrack(MainMenuMusic);
            
            try {
                
                MainMenuMusic = AudioSystem.getClip();
                MainMenuMusic.open(AudioSystem.getAudioInputStream(new File("res/music/01_MainMenu.wav")));
                
            } 
            catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                //¿Clase de manejo de errores?
            }
            
            MainMenuMusic.start();
        }
        else if (i==2) {
            
            setLastTrack(MainMenuMusic); //Indique 
            
            try {
                
                MainMenuMusic = AudioSystem.getClip();
                MainMenuMusic.open(AudioSystem.getAudioInputStream(new File("music/01_MainMenu.wav")));
                
            } 
            catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
                //¿Clase de manejo de errores?
            }
            
            MainMenuMusic.start();
            
        }
        
    }
    public void stopTrack(int i){
        if (i==1){
            
            MainMenuMusic.close();
            
        }
    }
    
//    public void setVolume(){
//        Clip control = getLastTrack();
//        stopTrack(getLastTrackNumber());
//        
//        
//        FloatControl gainControl =  (FloatControl) control.getControl(FloatControl.Type.MASTER_GAIN);
//        gainControl.setValue((float) -10.0);
//        
//        control.start();
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MusicPlayer;

import States.S0_MainMenu;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.newdawn.slick.Sound;

/**
 *
 * @author razvanvc
 */
public class MusicPlayer {

    /**
     *
     */
    public static Clip MainMenuMusic;

    public MusicPlayer() {
    }
    
    public static void init (){
        
    }
    
    public void playTrack(int i){
        //Clip track = n.;
        if (i == 1) {
            try {
                MainMenuMusic = AudioSystem.getClip();
                MainMenuMusic.open(AudioSystem.getAudioInputStream(new File("music/01_MainMenu.wav")));
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {}
            MainMenuMusic.start();
            
        }
        
    }
    public void stopTrack(int i){
        if (i==1){
            
            MainMenuMusic.close();
            
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.PlayableCharacter;
import MusicPlayer.MusicPlayer;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jgome
 */

import Entities.Characters.PlayableCharacter;
import MusicPlayer.*;
import static States.TutorialFight.lastStage;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class TutorialTransport extends BasicGameState{

    public String mouse = "No input yet!";
    Image play;

    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    private final Color background = new Color(0, 0, 255);
    public static int lastStage;
    private PlayableCharacter principal;
    private boolean playingMuscic = true;
    public MusicPlayer musicplayer = new MusicPlayer();
    private ObjectOutputStream save;
    
    public TutorialTransport(int state) {
    }
    
    
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        font = new Font("Italica", Font.ROMAN_BASELINE, 25);
        playersOptionsTTF = new TrueTypeFont(font, true);
        lastStage = sbg.getCurrentStateID();
        musicplayer.playTrack(1);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
            g.setLineWidth(4);
            playersOptionsTTF.drawString(50,100,"Controls: ", notChosen);
            playersOptionsTTF.drawString(150,150,"UP: up arrow / W", notChosen);
            playersOptionsTTF.drawString(150,200,"DOWN: down arrow / S", notChosen);
            playersOptionsTTF.drawString(150,250,"RIGHT: right arrow / D", notChosen);
            playersOptionsTTF.drawString(150,300,"LEFT: left arrow / A", notChosen);
            playersOptionsTTF.drawString(50,350,"Purpose: ", notChosen);
            playersOptionsTTF.drawString(150,400,"Get the last key fragment at the end of the map", notChosen);
            playersOptionsTTF.drawString(50,450,"Tips: ", notChosen);
            playersOptionsTTF.drawString(150,500,"Try to press enter on top of the pressure plates...", notChosen);
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        //Consigue la posicion del raton
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER))
        {
            sbg.enterState(23);
        }
        }
    
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 53;
    }
}


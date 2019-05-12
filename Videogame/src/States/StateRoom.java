/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Map.Mapa;
import static States.S0_MainMenu.lastStage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author jgome
 */
public class StateRoom extends BasicGameState{
    private Mapa map;
    private ObjectInputStream load;
    private boolean fog=true;
    public static final int room = 20;
    public static final int laberinth=21;
    public static final int puzzle=22;
    public static final int transport=23;
    public static final int bossfight=24;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private ObjectOutputStream save;
    private boolean start;
    public StateRoom(int state)
    {
        
    }
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start=true;
        map=new Mapa("src/Tiled/Habitacion.tmx", gc);
        int positionx=200, positiony=200;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
        g.setColor(Color.white);
        g.drawString("the position of the char= x: "+map.getX()+"y: "+map.getY(), 40, 40);
        map.getAnimation().draw(Char.getXPos(), Char.getYPos());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(start)
        {
            try {
            try {
            this.load=new ObjectInputStream(new FileInputStream("src/Archivo/Character.dat"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
            }
            Char=(PlayableCharacter) load.readObject();
            load.close();
            } catch (IOException ex) {
                Logger.getLogger(StatePuzzle.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(StatePuzzle.class.getName()).log(Level.SEVERE, null, ex);
            }
            map.setCharacter(Char);
            map.setSpeed(Char.getSpeed());
            start=false;
        }
        Input input = gc.getInput();
        map.Movimiento(i, gc);
        //map.getAnimation().update(i);
        interact=map.interact();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
        
    }
    public int getID() {
        return 20;
    }
    public void interact(Graphics g, StateBasedGame sbg, GameContainer gc)
    {
        Input input = gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            if(input.isKeyPressed(Input.KEY_ENTER))
            {
                try {
                    saveChar(Char);
                    }   catch (IOException ex) {
                    Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if(map.getX()<=367 && map.getX()>=333 && map.getY()<=45)
                {
                     sbg.enterState(laberinth);
                }
                if(map.getX()<=280 && map.getX()>=204 && map.getY()<=45)
                {
                     sbg.enterState(transport);
                }
                if(map.getX()<=148 && map.getX()>=108 && map.getY()<=49)
                {
                     sbg.enterState(puzzle);
                }
                if(map.getY()>=200)
                {
                    
                    if(Char.getKeys().getItems().size()<3)
                      sbg.enterState(25);
                    else
                      sbg.enterState(25);
                }
            }
            interact=false;
        }
    }
 public void saveChar(PlayableCharacter Character) throws IOException
    {
        try {
            this.save=new ObjectOutputStream(new FileOutputStream("src/Archivo/Character.dat"));
            //musicplayer.setVolume(); Implement function (dont work yet)
        } catch (FileNotFoundException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        save.reset();
        save.writeObject(Character);
        save.close();
    }
}


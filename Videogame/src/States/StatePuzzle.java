/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Entities.Items.Key;
import Map.*;
import static States.S0_MainMenu.lastStage;
import static States.StateRoom.bossfight;
import static States.StateRoom.laberinth;
import static States.StateRoom.puzzle;
import static States.StateRoom.transport;
import TextDisplay.TextDisplay;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
public class StatePuzzle extends BasicGameState{
    private TextDisplay td = null;
    private File archivo;
    private FileReader fr;
    private BufferedReader br;
    private String texto;
    private Image NPC;
    private boolean showText = false, read=false, times=true;
    private Input input = null;
    private Timer timer = null;
    private PlatformMap map;
    private ObjectInputStream load;
    private boolean transport=false;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private ObjectOutputStream save;
    private Key llave;
    private boolean start=true;
    public StatePuzzle(int state)
    {
        
    }
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start=true;
        try {
            this.load=new ObjectInputStream(new FileInputStream("src/Archivo/Character.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateTransport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StateTransport.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.save=new ObjectOutputStream(new FileOutputStream("src/Archivo/Character.dat"));
            //musicplayer.setVolume(); Implement function (dont work yet)
        } catch (FileNotFoundException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        llave=new Key("LaberinthKey", "21");
        map=new PlatformMap("src/Tiled/PlatformMap.tmx", gc);
        int positionx=396, positiony=203;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
        NPC=new Image("src/NPC/Idle.png");
        td=new TextDisplay(gc);
        timer = new Timer(true);
        archivo = new File("src/Archivo/Fragmento2.txt");
        try {
            fr = new FileReader (archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        br = new BufferedReader(fr);
    }

    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        try {
            interact(g, sbg, gc);
        } catch (IOException ex) {
            Logger.getLogger(StatePuzzle.class.getName()).log(Level.SEVERE, null, ex);
        }
        map.getAnimation().draw(Char.getXPos(), Char.getYPos());
        if(read)
        {
            g.setColor(Color.black);
            g.fillRect(0, 475, 800, 600);
            g.drawImage(NPC, 5, 500);
            if(isShowText())
            {
                td.displayText();
            }
        }
    }

    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input in = gc.getInput();
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
            map.Movimiento(i, gc, sbg);
            interact=map.interact();
            start=false;
        }
        if(read)
        {
            
            if(in.isKeyPressed(Input.KEY_ENTER))
            {
                try {
                    texto=br.readLine();
                    if(texto==null) read=false;
                    else  td.setText(texto, 100, 550);
                } catch (IOException ex) {
                    Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
                }
                    isTextShowing(true);
                    timer.schedule(new TimerTask()
                    {
                        public void run()
                            {
                                isTextShowing(false);
                            }
                    }, 3000);
            }
        }
        else
        {
            
            map.Movimiento(i, gc, sbg);
            interact=map.interact();
            try {
                recogerLlave(sbg, gc);
            } catch (IOException ex) {
                Logger.getLogger(StatePuzzle.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (in.isKeyPressed(Input.KEY_ESCAPE)) {
               sbg.enterState(5);
               lastStage = sbg.getCurrentStateID();
            }
        }
    }
    //Return the state of the menu (0)
    public int getID() {
        return 22;
    }

    public void interact(Graphics g, StateBasedGame sbg, GameContainer gc) throws IOException, SlickException
    {
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            interact=false;
        }
    }
    public void recogerLlave(StateBasedGame sbg, GameContainer gc) throws SlickException, IOException
    {
        Input input = gc.getInput();
        if(interact)
        {
            if(input.isKeyPressed(Input.KEY_ENTER))
            {
                if(times)
                {
                    llave.recogerllave(Char);
                    read=true;
                    times=false;
                }
                else
                {
                    saveChar(Char);
                    sbg.getState(20).init(gc, sbg);
                    sbg.enterState(20);
                }
            }
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
      public Input getInput()
    {
        return input;
    }
    
    public void isTextShowing(boolean newValue)
    {
        showText = newValue;
    }
    
    public boolean isShowText()
    {
        return showText;
    }
}
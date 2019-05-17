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
public class StateRoom extends BasicGameState{
    private TextDisplay td = null;
    private String texto;
    private boolean showText = false, read=true;
    private Input input = null;
    private Timer timer = null;
    private Mapa map;
    private Image NPC;
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
    private File archivo;
    private FileReader fr;
    private BufferedReader br;
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
        NPC=new Image("src/NPC/Idle.png");
        td=new TextDisplay(gc);
        timer = new Timer(true);
        archivo = new File("src/Archivo/Fragmento3.txt");
        try {
            fr = new FileReader (archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        br = new BufferedReader(fr);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
        g.setColor(Color.white);
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
            map.Movimiento(i, gc);
            //map.getAnimation().update(i);
            interact=map.interact();
            moveStates(sbg, gc);
            if (in.isKeyPressed(Input.KEY_ESCAPE)) {
               sbg.enterState(5);
               lastStage = sbg.getCurrentStateID();
            }
            try {
                    td.setText(br.readLine(), 100, 550);
                } catch (IOException ex) {
                    Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
                    read=false;
                }
                    isTextShowing(true);
                    timer.schedule(new TimerTask()
                    {
                        public void run()
                            {
                                isTextShowing(false);
                            }
                    }, 3000);
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
            map.Movimiento(i, gc);
            //map.getAnimation().update(i);
            interact=map.interact();
            moveStates(sbg, gc);
            if (in.isKeyPressed(Input.KEY_ESCAPE)) {
               sbg.enterState(5);
               lastStage = sbg.getCurrentStateID();
            }
        }
    }
    public int getID() {
        return 20;
    }
    public void interact(Graphics g, StateBasedGame sbg, GameContainer gc) throws SlickException
    {
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            interact=false;
        }
    }
    public void moveStates(StateBasedGame sbg, GameContainer gc) throws SlickException
    {
        Input in = gc.getInput();
        if(interact)
        {
            if(in.isKeyPressed(Input.KEY_ENTER))
            {
                try {
                    saveChar(Char);
                    }   catch (IOException ex) {
                    Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                if(map.getX()<=365 && map.getX()>=328 && map.getY()<=44)
                {
                     sbg.enterState(51);
                }
                if(map.getX()<=276 && map.getX()>=198 && map.getY()<=44)
                {
                     sbg.enterState(53);
                }
                if(map.getX()<=149 && map.getX()>=108 && map.getY()<=49)
                {
                     sbg.enterState(52);
                }
                if(map.getY()>=200)
                {
                    
                    if(Char.getKeys().getItems().size()<3)
                    {
                      sbg.getState(24).init(gc, sbg);
                      sbg.enterState(24);
                    }
                    else
                    {
                      sbg.getState(25).init(gc, sbg);
                      sbg.enterState(25);
                    }
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


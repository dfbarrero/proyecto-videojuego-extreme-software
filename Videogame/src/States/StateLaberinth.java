/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.Characters.Enemy;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Entities.Items.*;
import Map.Mapa;
import static States.S0_MainMenu.lastStage;
import static States.StateRoom.bossfight;
import static States.StateRoom.laberinth;
import static States.StateRoom.puzzle;
import static States.StateRoom.transport;
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
import static org.newdawn.slick.Color.black;
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
public class StateLaberinth extends BasicGameState{
    private Mapa map;
    private ObjectInputStream load;
    private boolean fog=true;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private Sword espada;
    private Bow arco;
    boolean sword=false, llaveb=false, bow=false, flechas=false;
    private int contfl=0;
    private Key llave;
    private ObjectOutputStream save;
    private boolean start=true;
    public StateLaberinth(int state)
    {
        
    }

    @Override
    public int getID() {
        return 21;
    }
    public void setCharacter(PlayableCharacter Char)
    {
        this.Char=Char;
    }
public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    start=true;    
    try {
            this.load=new ObjectInputStream(new FileInputStream("src/Archivo/Character.dat"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            this.save=new ObjectOutputStream(new FileOutputStream("src/Archivo/Character.dat"));
            //musicplayer.setVolume(); Implement function (dont work yet)
        } catch (FileNotFoundException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        map=new Mapa("src/Tiled/Laberinth.tmx", gc);
        int positionx=-625, positiony=-405;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
        llave=new Key("LLave lab", "1");
        arco=new Bow("Arco", 100, 0, "Arco");
        espada=new Sword("Espada", 50, 200, "Espada");
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        if(fog)
        {
            map.renderMap(gc, g, true);
            g.setColor(Color.white);
            interact(g, gc, sbg);
            g.setColor(black);
            g.fillRect(0, 0, gc.getWidth(), (float) (gc.getHeight()/(2.55)));
            g.fillRect(0, 0, (float) (gc.getWidth()/(2.4)), gc.getHeight());
            g.fillRect(0, (float) (gc.getHeight()/(1.54)), gc.getWidth(), (float) (gc.getHeight()/2.4));
            g.fillRect((float) (gc.getWidth()/(1.62)),0, gc.getWidth()/(2), gc.getHeight());
            
        }
        else
        {
            map.renderMap(gc, g, true);
            g.setColor(Color.white);
            interact(g, gc, sbg);
        }
        g.setColor(Color.black);
        g.drawString("the position of the char= x: "+map.getX()+"y: "+map.getY(), 40, 40);
        map.getAnimation().draw(Char.getXPos(), Char.getYPos());
    }

    @Override
    //Make possible the movement
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
        interact=map.interact();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
    }
    //Return the state of the menu (0)
    public void interact(Graphics g, GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        Input input=gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            if(input.isKeyDown(Input.KEY_ENTER))
            {
                if(fog)
                {
                    g.setColor(Color.white);
                }
                else g.setColor(black);
                
                if(map.getX()<=-205 && map.getX()>=-245 && map.getY()>=-475 && map.getY()<=-405)
                {
                    if(fog)fog=false;
                    g.drawString("The black fog has lifted!", (int) map.getCharacter().getXPos()-100, (int) map.getCharacter().getYPos()+50);
                }
                else if(map.getX()>=-60 && map.getX()<=-25 && map.getY()>=-955 && map.getY()<=-885)
                {
                    if(!sword)
                    {
                        espada.recoger(Char);
                        System.out.println("Espada recogida");
                        sword=true;
                    }
                    g.drawString("Sword acquired", (int) map.getCharacter().getXPos()-50, (int) map.getCharacter().getYPos()+50);
                }
                else if(map.getY()>=35 && map.getX()<=-1270 && map.getX()>=-1300)
                {
                    if(!bow)
                    {
                        arco.recoger(Char);
                        arco.addarrows(10);
                        System.out.println("Arco recogido");
                        contfl++;
                        bow=true;
                        flechas=true;
                    }
                    g.drawString("Bow acquired", (int) map.getCharacter().getXPos()-40, (int) map.getCharacter().getYPos()+50);
                }
                else if(map.getY()<=-1380 && map.getY()>=-1474)
                {
                    g.setColor(Color.white);
                    if(!llaveb)
                    {
                        llave.recogerllave(Char);
                        System.out.println("Llave recogida");
                        llaveb=true;
                    }
                    g.drawString("Key acquired", (int) map.getCharacter().getXPos()-40, (int) map.getCharacter().getYPos()+50);
                }
                else if(map.getX()<=-680 && map.getY()<=-1485)
                {
                    try {
                    saveChar(Char);
                    }   catch (IOException ex) {
                    Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    sbg.getState(20).init(gc, sbg);
                    sbg.enterState(20);
                }
                /*
                else if(!flechas)
                {
                    arco.addarrows(10);
                    g.setColor(black);
                    g.drawString("Arrows acquired! (10)", 350, 500);
                    System.out.println("Arrows areron");
                    contfl++;
                    flechas=true;
                }
                */
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
}

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
    private File archivo;
    private FileReader fr;
    private BufferedReader br;
    private Image NPC;
    private ObjectInputStream load;
    private boolean fog=true;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private String texto;
    private Sword espada;
    private Bow arco;
    boolean sword=false, llaveb=false, bow=false, flechas=false;
    private int contfl=0;
    private Key llave;
    private ObjectOutputStream save;
    private boolean start=true;
    private TextDisplay td = null;
    private boolean showText = false, read=false;
    private Input input = null;
    private Timer timer = null;
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
    fog=true;
    td=new TextDisplay(gc);
    timer = new Timer(true);
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
        arco=new Bow("Arco", 40, 0, "Arco");
        espada=new Sword("Espada", 75, 4, "Espada");
        NPC=new Image("src/NPC/Idle.png");
        td=new TextDisplay(gc);
        timer = new Timer(true);
        archivo = new File("src/Archivo/Fragmento1.txt");
        try {
            fr = new FileReader (archivo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StateRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
        br = new BufferedReader(fr);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        
        if(fog)
        {
            map.renderMap(gc, g, true);
            g.setColor(Color.white);
            try {
                interact(g, gc, sbg);
            } catch (IOException ex) {
                Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                interact(g, gc, sbg);
            } catch (IOException ex) {
                Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
        if(isShowText())
        {
            td.displayText();
        }
        g.setColor(Color.black);
        map.getAnimation().draw(Char.getXPos(), Char.getYPos());
    }

    @Override
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
            map.Movimiento(i, gc);
            interact=map.interact();
            try {
                interactionup(gc, sbg);
            } catch (IOException ex) {
                Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            interact=map.interact();
            try {
                interactionup(gc, sbg);
            } catch (IOException ex) {
                Logger.getLogger(StateLaberinth.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (in.isKeyPressed(Input.KEY_ESCAPE)) {

               sbg.enterState(5);
               lastStage = sbg.getCurrentStateID();
            }
        }
    }
    //Return the state of the menu (0)
    
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
  public void interact(Graphics g, GameContainer gc, StateBasedGame sbg) throws SlickException, IOException
    {
        Input in=gc.getInput();
        if(interact)
        {
                if(map.getX()<=-208 && map.getX()>=-245 && map.getY()>=-475 && map.getY()<=-405)
                {
                    if(fog) g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
                }
                else if(map.getX()>=-60 && map.getX()<=-15 && map.getY()>=-957 && map.getY()<=-885)
                {

                    if(!sword)  g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
                }
                else if(map.getY()>=26 && map.getX()<=-1264 && map.getX()>=-1300)
                {
                    if(!bow)    g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
                }
                else if(map.getY()<=-1380 && map.getY()>=-1474)
                {
                    if(!llaveb) g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
                }
                else if(map.getX()<=-680 && map.getY()<=-1485)
                {
                    g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
                }
                else if(map.getX()<=-591 && map.getX()>=-658 && map.getY()>=-210)
                {
                    g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);   
                }
                else
                {
                if(!flechas) g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);   
                }
            }
            interact=false;
        }
  public void interactionup(GameContainer gc, StateBasedGame sbg) throws IOException, SlickException
  {
      Input in=gc.getInput();
      if(interact)
        {
            
                if(map.getX()<=-208 && map.getX()>=-245 && map.getY()>=-475 && map.getY()<=-405)
                {

                    if(in.isKeyDown(Input.KEY_ENTER))
                    {
                        td.setText("The black fog has lifted!", (int) map.getCharacter().getXPos()-100, (int) map.getCharacter().getYPos()+45);
                        isTextShowing(true);
                        timer.schedule(new TimerTask()
                        {
                            public void run()
                            {
                                isTextShowing(false);
                            }
                        }, 3000);
                        if(fog)
                        {
                            fog=false;
                        }
                    }
                    }
                else if(map.getX()>=-60 && map.getX()<=-15 && map.getY()>=-957 && map.getY()<=-885)
                {
                    if(in.isKeyDown(Input.KEY_ENTER))
                    {
                        td.setText("Sword acquired", (int) map.getCharacter().getXPos()-50, (int) map.getCharacter().getYPos()+45);
                        isTextShowing(true);
                        timer.schedule(new TimerTask()
                        {
                            public void run()
                            {
                                isTextShowing(false);
                            }
                        }, 3000);
                        if(!sword)
                        {
                            espada.recoger(Char);
                            sword=true;
                        }
                    }
                }
                else if(map.getY()>=26 && map.getX()<=-1264 && map.getX()>=-1300)
                {
                    if(in.isKeyDown(Input.KEY_ENTER))
                    {
                        td.setText("Bow acquired", (int) map.getCharacter().getXPos()-40, (int) map.getCharacter().getYPos()+45);
                        isTextShowing(true);
                        timer.schedule(new TimerTask()
                        {
                            public void run()
                            {
                                isTextShowing(false);
                            }
                        }, 3000);
                        if(!bow)
                        {
                            arco.recoger(Char);
                            bow=true;
                        }
                    }
                }
                else if(map.getY()<=-1380 && map.getY()>=-1474)
                {
                    if(in.isKeyDown(Input.KEY_ENTER))
                    {
                        td.setText("Key acquired", (int) map.getCharacter().getXPos()-40, (int) map.getCharacter().getYPos()+45);
                        isTextShowing(true);
                        timer.schedule(new TimerTask()
                        {
                            public void run()
                            {
                                isTextShowing(false);
                            }
                        }, 3000);
                        if(!llaveb)
                        {
                            llave.recogerllave(Char);
                            llaveb=true;
                            read=true;
                        }
                    }
                }
                else if(map.getX()<=-680 && map.getY()<=-1485)
                {
                    if(in.isKeyPressed(Input.KEY_ENTER))
                    {
                            saveChar(Char);
                            sbg.getState(20).init(gc, sbg);
                            sbg.enterState(20);
                    }
                }
                else if(map.getX()<=-550 && map.getX()>=-700 && map.getY()>=-220)
                {
                        if(in.isKeyDown(Input.KEY_ENTER))
                        {
                            td.setText("This is an ancient statue", (int) map.getCharacter().getXPos()-10, (int) map.getCharacter().getYPos()+45);
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
                    if(in.isKeyDown(Input.KEY_ENTER))
                    {
                        td.setText("Arrows acquired (+10)", (int) map.getCharacter().getXPos()-35, (int) map.getCharacter().getYPos()+45);
                            isTextShowing(true);
                            timer.schedule(new TimerTask()
                            {
                                public void run()
                                {
                                    isTextShowing(false);
                                }
                            }, 3000);
                        if(!flechas)
                        {
                            arco.addarrows(10);
                            contfl++;
                            flechas=true;
                        }
                    }
                }
                }
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


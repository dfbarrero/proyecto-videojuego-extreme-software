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
public class StateTransport extends BasicGameState{
    private Image NPC;
    private File archivo;
    private FileReader fr;
    private BufferedReader br;
    private TextDisplay td = null;
    private String texto;
    private boolean showText = false, read=false, times=true;
    private Input input = null;
    private Timer timer = null;
    private Mapa map;
    private ObjectInputStream load;
    private boolean transport=false;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private ObjectOutputStream save;
    private Key llave;
    private boolean start=true;
    public StateTransport(int state)
    {
        
    }
    @Override
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
        map=new Mapa("src/Tiled/Transport.tmx", gc);
        int positionx=350, positiony=75;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
        NPC=new Image("src/NPC/Idle.png");
        td=new TextDisplay(gc);
        timer = new Timer(true);
        archivo = new File("src/Archivo/HistoriaPrincipal.txt");
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
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g, sbg, gc);
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
            transport(sbg, gc);
            
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
//                    timer.schedule(new TimerTask()
//                    {
//                        public void run()
//                            {
//                                isTextShowing(false);
//                            }
//                    }, 3000);
            }
        }
        else
        {
            
            map.Movimiento(i, gc);
            interact=map.interact();
            if (in.isKeyPressed(Input.KEY_ESCAPE)) {
               sbg.enterState(5);
               lastStage = sbg.getCurrentStateID();
            }
            transport(sbg, gc);
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 23;
    }
    public void transport(StateBasedGame sbg, GameContainer gc) throws SlickException
    {
        double x, y;
        double tx, ty;
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.KEY_ENTER) && interact)
            {
                //Bloque 1
                if(map.getX()<=366 && map.getX()>=299 && map.getY()<=-12 && map.getY()>=-91)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=200;
                    ty=-27;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=366 && map.getX()>=299 && map.getY()<=-175 && map.getY()>=-251)
                {
                     x=map.getX();
                    y=map.getY();
                    tx=325;
                    ty=-435;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=366 && map.getX()>=299 && map.getY()<=-400 && map.getY()>=-478)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=335;
                    ty=-220;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=366 && map.getX()>=299 && map.getY()<=-560 && map.getY()>=-638)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-180;
                    ty=-600;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 2
                else if(map.getX()<=244 && map.getX()>=167 && map.getY()<=212 && map.getY()>=130)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=325;
                    ty=-60;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=244 && map.getX()>=167 && map.getY()<=16 && map.getY()>=-64)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=70;
                    ty=-27;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=244 && map.getX()>=167 && map.getY()<=-203 && map.getY()>=-288)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-308;
                    ty=160;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=244 && map.getX()>=167 && map.getY()<=-460 && map.getY()>=-542)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=325;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 3
                else if(map.getX()<=116 && map.getX()>=41 && map.getY()<=16 && map.getY()>=-57)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=70;
                    ty=-470;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=116 && map.getX()>=41 && map.getY()<=-203 && map.getY()>=-286)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-50;
                    ty=-600;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=116 && map.getX()>=41 && map.getY()<=-429 && map.getY()>=-512)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=70;
                    ty=-27;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 4
                else if(map.getX()<=-10 && map.getX()>=-75 && map.getY()<=-556 && map.getY()>=-641)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=70;
                    ty=-250;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 5
                else if(map.getX()<=-74 && map.getX()>=-151 && map.getY()<=-111 && map.getY()>=-192)
                {
                    if(times)
                    {
                        llave.recogerllave(Char);
                        read=true;
                        times=false;
                    }
                    else
                    {
                        try {
                        saveChar(Char);
                        }   catch (IOException ex) {
                        Logger.getLogger(S0_MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        sbg.getState(20).init(gc, sbg);
                        sbg.enterState(20);
                    }
                }
                //Bloque 6
                else if(map.getX()<=-139 && map.getX()>=-215 && map.getY()<=-558 && map.getY()>=-637)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=325;
                    ty=-600;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                // Bloque 8
                else if(map.getX()<=-391 && map.getX()>=-473 && map.getY()<=16 && map.getY()>=-60)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-560;
                    ty=-120;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-391 && map.getX()>=-473 && map.getY()<=-175 && map.getY()>=-253)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-560;
                    ty=-400;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-391 && map.getX()>=-473 && map.getY()<=-364 && map.getY()>=-444)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-560;
                    ty=-535;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-391 && map.getX()>=-473 && map.getY()<=-497 && map.getY()>=-572)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-560;
                    ty=-670;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-391 && map.getX()>=-473 && map.getY()<=-623 && map.getY()>=-701)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-310;
                    ty=-440;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 9
                else if(map.getX()<=-522 && map.getY()<=-77 && map.getY()>=-157)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-440;
                    ty=-220;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-522 && map.getY()<=-370 && map.getY()>=-444)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-440;
                    ty=-410;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-522 && map.getY()<=-497 && map.getY()>=-571)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-440;
                    ty=-535;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-522 && map.getY()<=-624 && map.getY()>=-702)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-440;
                    ty=-665;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                //Bloque 7
                else if(map.getX()<=-267 && map.getX()>=-341 && map.getY()<=209 && map.getY()>=125)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-310;
                    ty=-130;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-267 && map.getX()>=-341 && map.getY()<=-81 && map.getY()>=-158)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-310;
                    ty=160;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                }
                else if(map.getX()<=-267 && map.getX()>=-341 && map.getY()<=-397 && map.getY()>=-479)
                {
                    x=map.getX();
                    y=map.getY();
                    tx=-120;
                    ty=-155;
                    map.setX(tx);
                    map.setY(ty);
                    map.actualizarMuros((float)(tx-x),(float)(ty-y));
                    map.actualizarIt((float)(tx-x),(float)(ty-y));
                } 
        }
    }
       public void interact(Graphics g, StateBasedGame sbg, GameContainer gc)
    {
        Input input = gc.getInput();
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
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

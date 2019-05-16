package States;

import Entities.Characters.Enemy;
import Entities.Characters.EnemyCharAnimation;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Map.BossMap;
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
public class BoosfightFinal extends BasicGameState{
    private BossMap map;
    private ObjectInputStream load;
    private boolean fog=true;
    private boolean interact=false;
    private PlayableCharacter Char;
    private ArrayList<NPC> npcs;
    private Enemy enemy;
    private boolean start=true;
    private ObjectOutputStream save;
    
    public BoosfightFinal(int state)
    {
        
    }
    public void setCharacter(PlayableCharacter Char)
    {
        this.Char=Char;
    }
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        start=true;
        map=new BossMap("src/Tiled/BossFightFinal.tmx", gc);
        int positionx=95, positiony=-275;
        map.setX(positionx);
        map.setY(positiony);
        map.actualizarIt(positionx,positiony);
        map.actualizarMuros(positionx,positiony);
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        map.renderMap(gc, g, true);
        g.setColor(Color.white);
        interact(g);
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
        try {
            interaction(gc, sbg);
        } catch (IOException ex) {
            Logger.getLogger(BoosfightFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 25;
    }
    public void interact(Graphics g)
    {
        if(interact)
        {
            g.drawString("INTERACT", (int) map.getCharacter().getXPos()-20, (int) map.getCharacter().getYPos()+32);
            interact=false;
        }
    }
    public void interaction(GameContainer gc, StateBasedGame sbg) throws IOException, SlickException
    {
        Input input=gc.getInput();
        if(interact && input.isKeyPressed(Input.KEY_ENTER))
        {
            saveChar(Char);
            sbg.getState(100).init(gc, sbg);
            sbg.enterState(100);
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

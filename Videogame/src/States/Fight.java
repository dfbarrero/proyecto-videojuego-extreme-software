package States;

import Entities.Characters.MainCharAnimation;
import Entities.Characters.Enemy;
import Entities.Characters.EnemyCharAnimation;
import Entities.Characters.NPC;
import Entities.Characters.PlayableCharacter;
import Entities.Items.Magic;
import Entities.Items.Weapon;
import Map.Mapa;
import MusicPlayer.MusicPlayer;
import static States.S0_MainMenu.lastStage;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
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
public class Fight extends BasicGameState{
    public String mouse = "No input yet!";
    Image play;
    Enemy boss;
    int xpos, ypos;
    private Animation bossanim;
    private int playersChoice = 0;
    private static final int NOCHOICES = 4;
    private static final int START = 0;
    private static final int LOAD = 1;
    private static final int OPTIONS = 2;
    private static final int QUIT = 3;
    private final String[] playersOptions = new String[NOCHOICES];
    private final Weapon[] armas=new Weapon[NOCHOICES];
    private Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    public static int lastStage;
    private PlayableCharacter principal;
    private boolean playingMuscic = true;
    public MusicPlayer musicplayer = new MusicPlayer();
    private Image background;
    private MainCharAnimation personaje;
    private boolean start;
    private EnemyCharAnimation bossAnim;
    private ObjectInputStream load;
    private PlayableCharacter Char;
    private Enemy enemy;
    private ObjectOutputStream save;
    private Image mainIdle;
    public Fight(int state)
    {
        
    }
    public void setCharacter(PlayableCharacter Char)
    {
        this.Char=Char;
    }
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background =new Image("src/Tiled/fight_back.gif");
        mainIdle=new Image("src/Sprites/FightIdle.png");
        font = new Font("Verdana", Font.ROMAN_BASELINE, 30);
        start=true;
        playersOptionsTTF = new TrueTypeFont(font, true);
        this.boss=new Enemy("id",(float)gc.getWidth()/2,(float) gc.getHeight()/2, "pCName",  0.15f, 200);
        lastStage = sbg.getCurrentStateID();
        this.bossAnim=new EnemyCharAnimation();
        musicplayer.playTrack(1);
        playersOptions[2] = "Magia";
        armas[2]=new Magic("Magic", 10, 10, "Magic");
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setColor(Color.white);
        g.drawImage(background, 10, 50);
        if(!start) g.drawImage(mainIdle, 150, 295);
        g.setLineWidth(4);
        g.setColor(Color.lightGray);
        g.drawRect(80, 460, 645, 200);
        renderPlayersOptions();
        g.setColor(Color.red);
        g.drawString("HP", 80, 410);
        g.drawString(Integer.toString(Char.getHp()), 435, 410);
        g.fillRect(80, 430, Char.getHp()*4, 15);
        g.setColor(Color.orange);
        g.drawString("Boss HP", 720, 10);
        g.drawString(Integer.toString(boss.getHp()), 190, 10);
        g.fillRect(190, 30, boss.getHp()*3, 15);
        g.drawString(mouse, 40, 40);
        
        g.drawAnimation(bossanim, 475, 180);
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
            start=false;
        }
        for(int cont=0;cont<Char.getInventory().getItems().size();cont++)
            {
                playersOptions[cont]=Char.getInventory().getItems().get(cont).name();
                armas[cont]=(Weapon) Char.getInventory().getItems().get(cont);
            }
        Input input=gc.getInput();
        xpos = Mouse.getX();
        ypos = Mouse.getY();
        mouse="x: "+xpos+ " y:"+ypos;
        animacionCombate(gc);
        combate(gc);
        if(Char.isDead() || boss.isDead())
        {
            sbg.enterState(20);
        }
        
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(5);
           lastStage = sbg.getCurrentStateID();
        }
    }
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 100;
    }
    private void renderPlayersOptions() {
        int max=Char.getInventory().getItems().size();
        if(max==1)
        {
            if(xpos>137 && xpos<255 && ypos>74 && ypos<130)    playersOptionsTTF.drawString(150, 480, playersOptions[0]);
            else    playersOptionsTTF.drawString(150, 480, playersOptions[0], notChosen);
            if(xpos>137 && xpos<255 && ypos<74)  playersOptionsTTF.drawString(150, 550, playersOptions[2]);
            else    playersOptionsTTF.drawString(150, 550, playersOptions[2], notChosen);
        }
        else if(max==2)
        {
            if(xpos>137 && xpos<255 && ypos>74 && ypos<130)    playersOptionsTTF.drawString(150, 480, playersOptions[0]);
            else    playersOptionsTTF.drawString(150, 480, playersOptions[0], notChosen);
            if(xpos>137 && xpos<255 && ypos<74)  playersOptionsTTF.drawString(150, 550, playersOptions[2]);
            else    playersOptionsTTF.drawString(150, 550, playersOptions[2], notChosen);
            if(xpos>465 && ypos>74 && ypos<130)    playersOptionsTTF.drawString(570, 480, playersOptions[1]);
            else    playersOptionsTTF.drawString(570, 480, playersOptions[1], notChosen);
        }
        else
        {
            if(xpos>137 && xpos<255 && ypos<74)  playersOptionsTTF.drawString(150, 550, playersOptions[2]);
            else    playersOptionsTTF.drawString(150, 550, playersOptions[2], notChosen);
        }
        
    }
    public void animacionCombate(GameContainer gc)
    {
        int max=Char.getInventory().getItems().size();
        Input input=gc.getInput();
        if(input.isKeyDown(Input.MOUSE_LEFT_BUTTON) || input.isKeyDown(Input.KEY_ENTER))
        {
            if(max==1)
            {
                if(xpos>137 && xpos<255 && ypos>74 && ypos<130)  bossanim=bossAnim.getEnemyCharAttackAnim();  ;
                if(xpos>137 && xpos<255 && ypos<74) bossanim=bossAnim.getEnemyCharAttackAnim();;
            }
            else if(max==2)
            {
                if(xpos>137 && xpos<255 && ypos>74 && ypos<130)   bossanim=bossAnim.getEnemyCharAttackAnim();;
                if(xpos>137 && xpos<255 && ypos<74)  bossanim=bossAnim.getEnemyCharAttackAnim();;
                if(xpos>465 && ypos>74 && ypos<130)   bossanim=bossAnim.getEnemyCharAttackAnim();;
            }
            else
            {
                if(xpos>137 && xpos<255 && ypos<74)  bossanim=bossAnim.getEnemyCharAttackAnim();;
            }
        }
        else 
        {
            bossanim=bossAnim.getEnemyCharIdleAnim();
        }
    }

    public void combate(GameContainer gc)
    {
        int max=Char.getInventory().getItems().size();
        Input input=gc.getInput();
        if(input.isKeyPressed(Input.MOUSE_LEFT_BUTTON) || input.isKeyPressed(Input.KEY_ENTER))
        {
            if(max==1)
            {
                if(xpos>137 && xpos<255 && ypos>74 && ypos<130)    atacar(armas[0]);
                if(xpos>137 && xpos<255 && ypos<74)  atacar(armas[2]);
            }
            else if(max==2)
            {
                if(xpos>137 && xpos<255 && ypos>74 && ypos<130)    atacar(armas[0]);
                if(xpos>137 && xpos<255 && ypos<74)  atacar(armas[2]);
                if(xpos>465 && ypos>74 && ypos<130)   atacar(armas[1]);
            }
            else
            {
                if(xpos>137 && xpos<255 && ypos<74)  atacar(armas[2]);
            }
        }
    }
    private void atacar(Weapon arma)
    {
        Char.atacar(arma, boss);
        boss.atacar(Char);
        
    }
}

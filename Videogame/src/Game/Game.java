/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Entities.Characters.PlayableCharacter;
import States.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import static java.lang.System.gc;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author razvanvc
 */
public class Game extends StateBasedGame {

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static final String gamename = "RemindMe";
    public static final int menu = 0;
    public static final int room = 20;
    public static final int laberinth=21;
    public static final int puzzle=22;
    public static final int transport=23;
    public static final int bossfight=24;
    public static final int bossfightfinal=25;
    public static final int loadpage = 2;
    public static final int savepage = 3;
    public static final int optionpage = 4;
    public static final int escpage = 5;
    public static final int sureexit = 6;
    public static final int graphics = 7;
    public static final int audio = 8;
    public static final int language = 9;
    public static final int ni = 10;
    public static final int end = 50;
    public static final int labTutorial = 51;
    public static final int platformTutorial= 52;
    public static final int transportTutorial = 53;
    public static final int fightTutorial = 54;
    public static int wight;
    public static int high;
    public static boolean fullscreen;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")

    public Game(String name) throws SlickException, IOException {
        super(gamename);
        this.addState((GameState) new S0_MainMenu(menu));
        this.addState((GameState) new StateRoom(room));
        this.addState((GameState) new StateLaberinth(laberinth));
        this.addState((GameState) new StatePuzzle(puzzle));
        this.addState((GameState) new StateTransport(transport));
        this.addState((GameState) new Bossfight(bossfight));
        this.addState((GameState) new S3_LoadPage(loadpage));
        this.addState((GameState) new S2_SavePage(savepage));
        this.addState((GameState) new S4_OptionsPage(optionpage));
        this.addState((GameState) new S5_EscPage(escpage));
        this.addState((GameState) new S6_ExitSure(sureexit));
        this.addState((GameState) new S7_Graphics(graphics));
        this.addState((GameState) new S8_Audio(audio));
        this.addState((GameState) new S9_Language(language));
        this.addState((GameState) new S10_NotImplementedYet(ni));
        this.addState((GameState) new BoosfightFinal(bossfightfinal));
        this.addState((GameState) new Fight(100));
        this.addState((GameState) new End(end));
        this.addState((GameState) new TutorialLab(labTutorial));
        this.addState((GameState) new TutorialPlatform(platformTutorial));
        this.addState((GameState) new TutorialTransport(transportTutorial));
        this.addState((GameState) new TutorialFight(fightTutorial));
    }

    /**
     * Inicializar la lista de estado
     *
     * @param gc
     * @throws SlickException
     */
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(menu).init(gc, this);
        this.getState(room).init(gc, this);
        this.getState(laberinth).init(gc, this);
        this.getState(puzzle).init(gc, this);
        this.getState(transport).init(gc, this);
        this.getState(bossfight).init(gc, this);
        this.getState(bossfightfinal).init(gc, this);
        this.getState(loadpage).init(gc, this);
        this.getState(savepage).init(gc, this);
        this.getState(optionpage).init(gc, this);
        this.getState(escpage).init(gc, this);
        this.getState(sureexit).init(gc, this);
        this.getState(graphics).init(gc, this);
        this.getState(audio).init(gc, this);
        this.getState(language).init(gc, this);
        this.getState(ni).init(gc, this);
        this.getState(100).init(gc, this);
        this.getState(50).init(gc, this);
        this.getState(labTutorial).init(gc, this);
        this.getState(platformTutorial).init(gc, this);
        this.getState(transportTutorial).init(gc, this);
        this.getState(fightTutorial).init(gc, this);
        this.enterState(menu);
        
    }

    public static void main(String[] args) throws FileNotFoundException {
        AppGameContainer appgc;

        //We charge the PROPERTIES file and take from there some values
        Properties prop = new Properties();

        FileInputStream ip;
        FileInputStream soconfig;

        //"lwjgl-2.9.3/native/macosx");
        //PATH
        //-Djava.library.path="/Users/razvanvc/Documents/GitHub/proyecto-videojuego-extreme-software/Videogame/lwjgl-2.9.3/native/macosx"
        try {

            ip = new FileInputStream("src/Game/config.properties");
            prop.load(ip);

            wight = Integer.parseInt(prop.getProperty("width"));
            high = Integer.parseInt(prop.getProperty("high"));
            fullscreen = Boolean.parseBoolean(prop.getProperty("fullscreen"));

            appgc = new AppGameContainer(new Game(gamename));
            appgc.setDisplayMode(800, 600, false);
            appgc.start();
        } catch (SlickException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            ip = new FileInputStream("src/Game/config.properties");
            prop.setProperty("width", "800");
            prop.setProperty("high", "600");
            prop.setProperty("fullScreen", "false");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public static boolean isWindows() {

        return (OS.contains("win"));

    }

    public static boolean isMac() {

        return (OS.contains("mac"));

    }
}

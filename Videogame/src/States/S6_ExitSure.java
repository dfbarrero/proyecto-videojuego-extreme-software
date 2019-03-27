/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import static States.S0_MainMenu.lastStage;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author razvanvc
 */
public class S6_ExitSure extends BasicGameState{

    private int playersChoice = 0;
    private static final int YES = 0;
    private static final int NO = 1;
    private static final int NOCHOICES = 2;
    private final String[] playersOptions = new String[NOCHOICES];
    private java.awt.Font font;
    private TrueTypeFont playersOptionsTTF;
    private final Color notChosen = new Color(153, 204, 255);
    
    public S6_ExitSure(int state) {
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playersOptions[0] = "Yes";
        playersOptions[1] = "No";
        
        font = new Font("Verdana", Font.BOLD, 40);
        playersOptionsTTF = new TrueTypeFont(font, true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString("Your in sure page",100,100);
        renderExit();
        playersOptionsTTF.drawString(100, 50, "Do you want to exit?", notChosen);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
           sbg.enterState(lastStage);
        }
        if (input.isKeyPressed(Input.KEY_DOWN)) {
            if (playersChoice == (NOCHOICES - 1)) {
                playersChoice = 0;
            } else {
                playersChoice++;
            }
        }
        
        if (input.isKeyPressed(Input.KEY_UP)) {
            if (playersChoice == 0) {
                playersChoice = NOCHOICES - 1;
            } else {
                playersChoice--;
            }
        }
        
        if (input.isKeyPressed(Input.KEY_ENTER)){
            switch (playersChoice){
                case YES:
                    gc.exit();
                    break;
                case NO:
                    if (lastStage == 1){
                        sbg.enterState(5);
                    } else{
                        sbg.enterState(0);
                    }
                    break;
                    
            }
            
        }
    }
    @Override
    public int getID() {
        return 6;
    }
    private void renderExit(){
        for (int i = 0; i < NOCHOICES; i++){
            if (playersChoice == i){
                playersOptionsTTF.drawString(100, i*50+200, playersOptions[i]);
            } else {
                playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions[i], notChosen);
            }
        }
    }
    
}

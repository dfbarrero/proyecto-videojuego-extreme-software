/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author razvanvc
 */
public class PrincipalMenu extends BasicGameState{

    public String mouse = "No input yet!";
    
    public PrincipalMenu(int satte) {
        
    }
    
    @Override
    //Initialice some stuff (dont know yet)
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
    }

    @Override
    //Draws things on the screen
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawString(mouse, 50, 50);//muestra la posicion de raton
        g.drawRect(50, 100, 120, 60);//Dibujas un rectangulo (x,y,longitud,altura
        //g.drawOval(200, 100, 120, 120);//dibjas una circunferencia (x,y,longitud, altura
        
        //Image test = new Image("res/Unknown.png"); //Creamos un objeto imagen 
        //g.drawImage(test, 200, 130);//dibujas la imagen test en las coordenadas (200,130)
        
        
    }

    @Override
    //Make possible the movement
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        mouse = xpos + " " + ypos; //cambia la variable de la posicion del raton
    }
    
    @Override
    //Return the state of the menu (0)
    public int getID() {
        return 0;
    }

}

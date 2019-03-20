/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entorno;
import java.util.ArrayList;
import org.newdawn.slick.*;

/**
 *
 * @author jgome
 */
public class Entorno {
    private Map background;
    private ArrayList<Additionals> buildings;
    public Entorno() throws SlickException
    {    
        this.background=new Map(0, 0, "");
        this.buildings=new ArrayList();
    }

    public Map getBackground() {
        return background;
    }

    public void setBackground(Map background) {
        this.background = background;
    }

    public ArrayList<Additionals> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Additionals> buildings) {
        this.buildings = buildings;
    }
    
}
   

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;

import Entities.Characters.PlayableCharacter;

/**
 *
 * @author Javier Muñoz
 */
public abstract class Item
{
    /*
    *Items can be "weapons" or "keys" depending on their ID and work differently
    *depending on it, so make sure to assign the correct ID to every item.
    */
    private String id;
    
    public Item(String id)
    {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public abstract String toString();
    
    public void recoger(PlayableCharacter personaje)
    {
        personaje.getInventory().addItem(this);
    }
}

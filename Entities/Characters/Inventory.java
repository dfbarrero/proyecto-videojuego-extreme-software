/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Characters;

import Entities.Items.Item;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Javier Muñoz
 */
public class Inventory implements Serializable
{
    private int maxInvSize = 100;
    private ArrayList<Item> items;
    
    public Inventory()
    {
        items = new ArrayList<Item>();
    }
    public void addItem(Item item)//This method adds the item to the first free
                                  //slot in the inventory. If the inventory is full
                                  //it will do nothing.
    {
        if(items.size()<maxInvSize)    items.add(item);
    }
    
    public Item dropItem(int position) //This method lets you drop an item from 
                                       //your inv. given a position in it.
    {
       Item item=items.get(position);
       items.remove(item);
       return item;
    }
    
    public void moveItem(int pos1, int pos2)//This method lets you swap an item
                                            //from your inv. with another one or
                                            //simply change the position of the item.
    {
        if(items.get(pos2) == null)
        {
            items.set(pos2,items.get(pos1));
            items.set(pos1, null);
        }
        else if(items.get(pos2) == null)
        {
            items.set(pos1, items.get(pos2));
            items.set(pos2, null);
        }
        else
        {
            Item temp1 = items.get(pos1);
            Item temp2 = items.get(pos2);
            items.set(pos1, temp2);
            items.set(pos2, temp1);
        }
    }
    
    public Item getSelectedItem(int position)//Returns the position of the selected item
    {
        return this.items.get(position);
    }

    public int getMaxInvSize()
    {
        return maxInvSize;
    }

    public void setMaxInvSize(int maxInvSize)
    {
        this.maxInvSize = maxInvSize;
    }

    public ArrayList<Item> getItems()
    {
        return items;
    }
    
}

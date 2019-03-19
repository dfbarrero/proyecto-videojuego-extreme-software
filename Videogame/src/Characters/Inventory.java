/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Characters;

/**
 *
 * @author Javier Muñoz
 */
public class Inventory
{
    private int maxInvSize = 100;
    private Item[] items;
    
    public Inventory()
    {
        items = new Item[maxInvSize];
    }
    public void addItem(Item item)//This method adds the item to the first free
                                  //slot in the inventory. If the inventory is full
                                  //it will do nothing.
    {
        int slot = freeSlot();
        if (slot>=0 && slot<getMaxInvSize()-1)
        {
            items[slot] = item;
        }
    }
    
    public Item dropItem(int position) //This method lets you drop an item from 
                                       //your inv. given a position in it.
    {
        Item droppedItem = getItems()[position];
        items[position] = null;
        return droppedItem;
    }
    
    public void moveItem(int pos1, int pos2)//This method lets you swap an item
                                            //from your inv. with another one or
                                            //simply change the position of the item.
    {
        if(getItems()[pos2] == null)
        {
            items[pos2] = getItems()[pos1];
            items[pos1] = null;
        }
        else if(getItems()[pos1] == null)
        {
           items[pos1] = getItems()[pos2];
           items[pos2] = null; 
        }
        else
        {
            Item temp1 = getItems()[pos1];
            Item temp2 = getItems()[pos2];
            items[pos1] = temp2;
            items[pos2] = temp1;
        }
    }
    
    public Item getSelectedItem(int position)//Returns the position of the selected item
    {
        return getItems()[position];
    }
    
    public int freeSlot()//This method looks for an empty space on the inv. and
                         //returns it. If the inv. is full, it returns -1.
    {
        boolean found = false;
        int i = 0;
        int freeSlot = -1;
        while(i<getItems().length||!found)
        {
            if(getItems()[i]==null)
            {
                found = true;
                freeSlot = i;
            }
            i++;
        }
        return freeSlot;
    }

    public int getMaxInvSize()
    {
        return maxInvSize;
    }

    public void setMaxInvSize(int maxInvSize)
    {
        this.maxInvSize = maxInvSize;
    }

    public Item[] getItems()
    {
        return items;
    }
    
}

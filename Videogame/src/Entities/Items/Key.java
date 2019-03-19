/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.Items;

/**
 *
 * @author Javier Muñoz
 */
public class Key extends Item
{
    private String name;
    
    public Key(String name, String id)
    {
        super(id);
        this.name = name;
    }
    
    @Override
    public String toString()
    {
        return "This is a key. It has a name tag: "+name;
    }
}

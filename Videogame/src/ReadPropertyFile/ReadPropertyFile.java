/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadPropertyFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author razvanvc
 */
public class ReadPropertyFile {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Properties prop=new Properties();
        FileInputStream ip;
        ip = new FileInputStream("/Users/razvanvc/Documents/GitHub/proyecto-videojuego-extreme-software/Videogame/src/ReadPropertyFile/config.properties");
        prop.load(ip);
        
        int wight;
        wight = Integer.parseInt(prop.getProperty("width"));
        System.out.println(wight);
        int high;
        high = Integer.parseInt(prop.getProperty("high"));
        System.out.println(high);
    }
}

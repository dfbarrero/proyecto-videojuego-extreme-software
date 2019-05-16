/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TextDisplay;

import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author Javier Muñoz
 */
public class TextDisplay
{
    private Font font;
    private TrueTypeFont ttf;
    private String text = "";
    private GameContainer gc;
    private int xPos;
    private int yPos;
    
    public TextDisplay(GameContainer gc)
    {
        font = new Font("Verdana", Font.PLAIN, 12);
        ttf = new TrueTypeFont(font, true);
        this.gc = gc;
    }

    public void displayText()
    {
        gc.getGraphics().setColor(Color.white);
        gc.getGraphics().drawString(text, xPos, yPos);
    }
    
    /**
     * @return the font
     */
    public Font getFont()
    {
        return font;
    }

    /**
     * @param font the font to set
     */
    public void setFont(Font font)
    {
        this.font = font;
    }

    /**
     * @return the TrueTypeFont object
     */
    public TrueTypeFont getTtf()
    {
        return ttf;
    }

    /**
     * @param ttf the TrueTypeFont object to set
     */
    public void setTtf(TrueTypeFont ttf)
    {
        this.ttf = ttf;
    }

    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }

    /**
     * @param text the text to set
     * @param xPos the x position of where the text is going to be drawn
     * @param yPos the y position of where the text is going to be drawn
     */
    public void setText(String text, int xPos, int yPos)
    {
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
    }
}

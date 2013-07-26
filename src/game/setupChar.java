/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 * 
 * class responsible for choosing the type of character
 * 
 */

import com.Enjyn.PlayerClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;

public class setupChar extends BasicGameState {
    private PlayerClass player;
    private Image selDarhyl;
    private Image selRose;
    private Image startImage;
    public setupChar(int id)
    {
    
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
    
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
    
    }
    
    public int getID()
    {
        return 1;
    }
    
    public PlayerClass getPlayerInfo()
    {
        return player;
    }
}

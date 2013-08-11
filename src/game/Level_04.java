/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import com.Enjyn.*;

public class Level_04 extends BasicGameState{
    private static PlayerClass player;
    private static int score;
    private BlockMap bmap;
    private boolean isPause;
    private boolean isGameOver;
    public Level_04(int id)
    {
    
    }
    
    public Level_04(int score, PlayerClass p)
    {
        this.score = score;
        this.player = player;
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        isPause = false;
        isGameOver = false;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
            
            }
            else
            {
            
            }
        }
        else
        {
            
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
            
            }
            else
            {
            
            }
        }
        else
        {
            
        }
    }
    
    public int getID()
    {
        return 5;
    }
}

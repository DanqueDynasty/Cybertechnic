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
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import com.Enjyn.*;

public class Level_02 extends BasicGameState {
    
    private BlockMap bmap;
    private boolean isPause;
    private boolean isGameOver;
    private PlayerClass player;
    private int score;
    
    public Level_02(int id)
    {
    
    }
    
    public Level_02(int score, PlayerClass p)
    {
        this.score = score;
        this.player = p;
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
        return 3;
    }
}

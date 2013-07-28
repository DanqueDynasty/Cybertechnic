/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 */

import com.Enjyn.BlockMap;
import com.Enjyn.PlayerClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;

public class Level_01 extends BasicGameState {
    private PlayerClass player;
    private setupChar _charAttrib;
    private BlockMap bmap;
    boolean isPause;
    boolean isGameOver;
    public Level_01(int id)
    {
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        
        bmap = new BlockMap("./res/Level_01.tmx");
    }
    
    public void setPlayer(PlayerClass player)
    {
        this.player = player;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                player.setControl(gc, delta, bmap);
            }else{
            
            }   
        }else{
        //still part of gameOver loop
            
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        bmap.tmap.render(0, 0);
    }
    
    public int getID()
    {
        return 2;
    }
}

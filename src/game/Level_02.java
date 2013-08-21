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
import org.newdawn.slick.geom.Vector2f;

public class Level_02 extends BasicGameState {
    
    private BlockMap bmap;
    private boolean isPause;
    private boolean isGameOver;
    private static PlayerClass player;
    private static int score;
    
    public Level_02(int id)
    {
    
    }
    
    public Level_02(int score, PlayerClass p)
    {
        this.score = score;
        this.player = p;
        player.setVector(new Vector2f(0, 1000));
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        bmap = new BlockMap("./res/map/Level_02.tmx");
        isGameOver = false;
        isPause = false;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                player.setControl(gc, delta, bmap);
                player.update(delta);
                
            }
        }
        else
        {
            //sbg.enterState(0);
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                float xOffset = 0;
                float yOffset = 435;
                
                float mapX = bmap.tmap.getWidth() - player.getVector().getX() + xOffset;
                float mapY = bmap.tmap.getHeight() - player.getVector().getY() + yOffset;
                
                g.translate(mapX, mapY);
                player.render(g);
                bmap.tmap.render(0, 0);
                //temp
                g.draw(player.getPolygon());
                g.drawImage(player.getMasterImage(), 
                            player.getVector().getX(),
                            player.getVector().getY());
                g.resetTransform();
                g.drawString("Score: " + score, 0, 0);
                g.drawString("Health: " + player.getHealth(), 0, 32);  
            }
        }
    }
    
    public int getID()
    {
        return 3;
    }
}

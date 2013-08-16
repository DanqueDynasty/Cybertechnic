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
import org.newdawn.slick.geom.Vector2f;
import com.Enjyn.*;
import java.util.ArrayList;

public class Level_02 extends BasicGameState {
    
    private BlockMap bmap;
    private boolean isPause;
    private boolean isGameOver;
    private static PlayerClass player;
    private static int score;
    private ArrayList<EnemyClass> enemyType1;
    
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
        bmap = new BlockMap("./res/Level_02.tmx");
        isPause = false;
        isGameOver = false;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                //main Game code
                player.setControl(gc, delta, bmap);
                player.update(delta);
            }
        }else{
            sbg.enterState(0);
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        if(!isGameOver)
        {
            if(isPause)
            {
                float xOffset = 0;
                float yOffset = 0;
                
                float mapX = bmap.tmap.getTileWidth() - player.getVector().getX() + xOffset;
                float mapY = bmap.tmap.getTileHeight() - player.getVector().getY() + yOffset;
                g.translate(mapX, mapY);
                player.render(g);
                g.drawImage(player.masterImage, player.getVector().getX(), player.getVector().getY());
                bmap.tmap.render(0, 0);
                g.resetTransform();
                g.drawString("Score: " + score, 0, 0);
                g.drawString("Health: " + player.getHealth(), 0, 32);
            }
            else
            {
                //display code for menu
            }
        }
    }
    
    public int getID()
    {
        return 3;
    }
}

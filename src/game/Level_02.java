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
import java.util.ArrayList;

public class Level_02 extends BasicGameState {
    
    private BlockMap bmap;
    private boolean isPause;
    private boolean isGameOver;
    private static PlayerClass player;
    private static int score;
    private ArrayList<EnemyClass> enemy;
    
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
        enemy = new ArrayList<>();
        for(int i = 0; i < 4; i++)
        {
            enemy.add(new EnemyClass(new Vector2f(0, 0), 64, 80, .5f, .25f, 1));
            enemy.get(i).setPosOffset(512);
            enemy.get(i).setDisOffset(128);
            enemy.get(i).setType(0);
            enemy.get(i).setHealth(50);
        }
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
                for(int i = 0; i < enemy.size(); i++)
                {
                    player.handleDamage(enemy.get(i));
                    if(player.getHealth() <= 0)
                    {
                        isGameOver = true;
                    }
                    
                    enemy.get(i).update(player, bmap, delta);
                    enemy.get(i).handleDamage(player);
                    if(enemy.get(i).health <= 0)
                    {
                        enemy.remove(i);
                        score = score + 4;
                    }
                }
            }
        }
        else
        {
            sbg.enterState(0);
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
                for(int i = 0; i < enemy.size(); i++)
                {
                    g.draw(enemy.get(i).getPolygon());
                }
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

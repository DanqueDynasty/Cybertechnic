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
import com.Enjyn.EnemyClass;
import com.Enjyn.ProjectileClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

public class Level_01 extends BasicGameState {
    private static PlayerClass player;
    private ArrayList<EnemyClass> enemyType1;
    private ArrayList<ProjectileClass> playerBullet;
    private ArrayList<ProjectileClass> enemyBullet;
    private setupChar _char;
    private static int score;
    private BlockMap bmap;
    boolean isPause;
    boolean isGameOver;
    public Level_01(int id)
    {
        
    }
    
    public Level_01(int score, PlayerClass p)
    {
        this.player = p;
        player.setVector(new Vector2f(0,1000));
        this.score = score;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
         bmap = new BlockMap("./res/Level_01.tmx");  
         enemyType1 = new ArrayList<>();
         for(int i = 0; i < 4; i++)
         {
             enemyType1.add(new EnemyClass(new Vector2f(0, 0), 64, 80, .5f, .25f, 1));
             enemyType1.get(i).setPosOffset(512);
             enemyType1.get(i).setDisOffset(128);
             enemyType1.get(i).setType(0);
             enemyBullet = enemyType1.get(i).bullet.getProjectile();
         }
         
         //playerBullet = player.bullet.getProjectile();
         isPause = false;
         isGameOver = false;
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                player.setControl(gc, delta, bmap);
                player.update(delta);
            }else{
                
            }   
        }else{
        //still part of gameOver loop
            
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        float xOffset = 0;
        float yOffset = 0;
        
        if(player.getVector().getX() <= 512)
        {
            xOffset = 128;
        }else{
            xOffset = 256;
        }
        
        float mapX = bmap.tmap.getTileWidth() - player.getVector().getX() + xOffset;
        float mapY = bmap.tmap.getTileHeight() - player.getVector().getY() + 650;
        
        g.translate(mapX, mapY);
        bmap.tmap.render(0, 0);
        if(player.getGender() == 0)
        {
            g.setColor(Color.blue);
        }else if( player.getGender() == 1)
        {
            g.setColor(Color.pink);
        }
        g.draw(player.getPolygon());
        g.resetTransform();
    }
    
    public PlayerClass getPlayerClass()
    {
        return player;
    }
    
    @Override
    public int getID()
    {
        return 2;
    }
}

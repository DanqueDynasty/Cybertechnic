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
import com.Enjyn.OverheadGuiClass;
import com.Enjyn.ProjectileClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

public class Level_01 extends BasicGameState {
    private static PlayerClass player;
    private ArrayList<EnemyClass> enemyType1;
    private static int score;
    private BlockMap bmap;
    boolean isPause;
    boolean isGameOver;
    private OverheadGuiClass gui;
    public Level_01(int id)
    {
        
    }
    
    public Level_01(int score, PlayerClass p, ArrayList<ProjectileClass> playerBullet)
    {
        this.player = p;
        player.setVector(new Vector2f(0,1000));
        this.score = score;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
         bmap = new BlockMap("./res/map/Level_01.tmx");  
         enemyType1 = new ArrayList<EnemyClass
                 >();
         for(int i = 0; i < 4; i++)
         {
             enemyType1.add(new EnemyClass(new Vector2f(0, 0), 64, 80, .5f, .25f, 1));
             enemyType1.get(i).setPosOffset(512);
             enemyType1.get(i).setDisOffset(128);
             enemyType1.get(i).setType(0);
             enemyType1.get(i).setHealth(50);
         }
         enemyType1.get(0).setVector(new Vector2f(0, 0));
         enemyType1.get(1).setVector(new Vector2f( 200 ,1000));
         
         //playerBullet = player.bullet.getProjectile();
         isGameOver = false;
         
         if(isGameOver == true)
         {
             isGameOver = false;
         }
         
         gui = new OverheadGuiClass();
         gui.initPauseScreen(gc);
         gui.setPauseStatus(false);
         isPause = gui.getPauseStatus();
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        Input input = gc.getInput();
        if(!isGameOver)
        {
            if(!isPause)
            {
                if(input.isKeyPressed(Input.KEY_ESCAPE))
                {
                    isPause =! isPause;
                }
                
                player.setControl(gc, delta, bmap);
                player.update(delta);
                for(int i = 0; i < enemyType1.size(); i++)
                {
                    player.handleDamage(enemyType1.get(i));
                    if(player.getHealth() <= 0)
                    {
                        isGameOver = true;
                    }
                    if(player.getVector().getY() >= bmap.mapHeight)
                    {
                        isGameOver = true;
                    }
                    enemyType1.get(i).update(player, bmap, delta);
                    enemyType1.get(i).handleDamage(player);
                    if(enemyType1.get(i).health <= 0)
                    {
                        enemyType1.remove(i);
                        score = score + 4;
                    }
                    
                }
                if(player.getVector().getX() >= gc.getWidth())
                {
                    Level_02 lvl = new Level_02(score, player);
                    sbg.enterState(3);
                }
                System.out.println("PlayerSpeed: " + player.getSpeed());
            }else{
                //pause loop
                gui.updatePauseScreen(gc, sbg, input);
                if(input.isKeyPressed(Input.KEY_ESCAPE))
                {
                    isPause =! isPause;
                }
                
            }   
        }else{
        //still part of gameOver loop
            sbg.enterState(0);
        }
    }
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        if(isGameOver == false)
        {
            if(isPause == false)
            {
                float xOffset = 0;
                float yOffset = 0;
        
            if(player.getVector().getX() <= 512)
            {
            //xOffset = 128;
            xOffset++;
            if(xOffset == 128)
            {
                xOffset += 0;
            }
            }else{
            //xOffset = 256;
                   xOffset++;
             if(xOffset == 256)
             {
                        xOffset += 0;
                    }
             }
        
                float mapX = bmap.tmap.getTileWidth() - player.getVector().getX() + xOffset;
                float mapY = bmap.tmap.getTileHeight() - player.getVector().getY() + 650;
        
                g.translate(mapX, mapY);
                player.render(g);
                bmap.tmap.render(0, 0);
                if(player.getGender() == 0)
                {
                    g.setColor(Color.blue);
                }else if( player.getGender() == 1)
                {   
                    g.setColor(Color.pink);
                }
                g.draw(player.getPolygon());
                g.drawImage(player.masterImage, player.getPolygon().getX(), player.getPolygon().getY());
                for(int i = 0; i < enemyType1.size(); i++)
                {
                    g.setColor(Color.red);
                    g.draw(enemyType1.get(i).getPolygon());
                    enemyType1.get(i).render(g);
                }
                g.resetTransform();
                g.setColor(Color.white);
                g.drawString("Score: " + score, 0, 0);
                g.drawString("Health: " + player.getHealth(), 0, 32);
            }
            else
            {
                gui.renderPauseScreen(gc, g);
            }
        }
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

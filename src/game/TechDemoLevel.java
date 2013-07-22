package game;

/**
 *
 * @author Nathan
 * Primary level for testing and debugging future concepts
 */

//imports

import com.Enjyn.Block;
import com.Enjyn.PlayerClass;
import com.Enjyn.BlockMap;
import com.Enjyn.EnemyClass;
import com.Enjyn.ProjectileClass;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import java.util.ArrayList;

public class TechDemoLevel extends BasicGameState {
    
    private BlockMap bMap;
    private PlayerClass player;
    private Vector2f startPos;
    private float mapX, mapY;
    private boolean isPause;
    private boolean isGameOver;
    private ArrayList<EnemyClass> type1Enemy;
    private ArrayList<ProjectileClass> playerBullet;
    private ArrayList<ProjectileClass> enemyBullet;
    
    public TechDemoLevel(int id)
    {
    
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        startPos = new Vector2f(100, 650);
        bMap = new BlockMap("./res/demoMap.tmx");
        player = new PlayerClass(startPos, 64, 80, .25f, 0);
        player.setDirection(1);
        player.setWeapon(1);
        type1Enemy = new ArrayList<>();
        playerBullet = player.bullet.getProjectile();
        for(int i = 0; i < 3; i++)
        {
            type1Enemy.add(new EnemyClass(new Vector2f(800, 665), 64, 64, .25f, 5, 1));
            type1Enemy.get(i).setPosOffset(256);
            type1Enemy.get(i).setDisOffset(128);
            type1Enemy.get(i).setType(0);
            enemyBullet = type1Enemy.get(i).bullet.getProjectile();
        }
        type1Enemy.get(0).setVector(new Vector2f(800, 665));
        type1Enemy.get(1).setVector(new Vector2f(800, 430));
        type1Enemy.get(2).setVector(new Vector2f(1024, 665));
        mapX = 0;
        mapY = 0;
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
                player.setControl(gc, delta, bMap); 
                if(player.getVector().getX() >= 512 && player.getVector().getX() <= 1536)
                {
                    if(gc.getInput().isKeyDown(Input.KEY_D))
                    {
                        if(player.getKeyPressed())
                        {
                            mapX -= player.getSpeed() * delta;
                        }
                    }
        
                    if(gc.getInput().isKeyDown(Input.KEY_A))
                    {
                        mapX += player.getSpeed() * delta;
                    }
                }else{
                if(gc.getInput().isKeyDown(Input.KEY_D))
                {
                    if(player.getKeyPressed())
                    {
                        mapX -= 0 * delta;
                    }
                }
        
                if(gc.getInput().isKeyDown(Input.KEY_A))
                {
                    mapX += 0* delta;
                }
                }
            for(int i = 0; i < type1Enemy.size(); ++i)
            {
                if(i < type1Enemy.size())
                    type1Enemy.get(i).update(player, bMap, delta);
            }
            
            for(int i = 0; i < playerBullet.size(); i++)
            {
                playerBullet.get(i).update(gc, delta, player);
            }
            }
        }
        
        
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        if(!isGameOver)
        {
            if(!isPause)
            {
                g.setDrawMode(Graphics.MODE_NORMAL);
                g.translate(mapX, mapY);
                bMap.tmap.render(0, 0);
                for(int i = 0; i < bMap.entities.size(); i++)
                {
                    Block tile = (Block) bMap.entities.get(i);
                    g.setColor(Color.blue);
                    g.draw(tile.poly);
                }
                g.setDrawMode(Graphics.MODE_SCREEN);
                g.setColor(Color.white);
                g.draw(player.getPolygon());
                for(int i = 0; i < type1Enemy.size(); i++)
                {
                    g.draw(type1Enemy.get(i).getPolygon());
                    g.draw(type1Enemy.get(i).viewPoly);
                    for(int j = 0; j < enemyBullet.size(); j++)
                    {
                        g.draw(enemyBullet.get(j).getPolygon());
                    }
                }
                
                for(int i = 0; i < playerBullet.size(); i++)
                {
                    g.draw(playerBullet.get(i).getPolygon());
                }
                
                g.setColor(Color.yellow);
                g.draw(player.groundPoly);
                g.setColor(Color.cyan);
                g.setDrawMode(Graphics.MODE_NORMAL);
                g.drawString("1", player.groundPoly.getX(), player.groundPoly.getY());
                g.drawString("2", player.groundPoly.getX(), player.groundPoly.getY() + player.getHeightOffset() - 5);
                g.drawString("3", player.groundPoly.getX() + player.getWidthOffset(), player.groundPoly.getY() + player.getHeightOffset() - 5);
                g.drawString("4", player.groundPoly.getX() + player.getWidthOffset(), player.groundPoly.getY());
                g.resetTransform();
        
                System.out.println("Size of PlayerBulletArray" + String.valueOf(playerBullet.size()));
            }
        }
        
        
    }
    
    public int getID()
    {
        return 0;
    }
}

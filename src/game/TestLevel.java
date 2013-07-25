/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 * second Test Level
 */

import com.Enjyn.BlockMap;
import com.Enjyn.EnemyClass;
import com.Enjyn.PlayerClass;
import com.Enjyn.ProjectileClass;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;


public class TestLevel extends BasicGameState {
    private BlockMap bmap;
    private PlayerClass player;
    private Vector2f startPos;
    private float mapX, mapY;
    private boolean isPause;
    private boolean isGameOver;
    private ArrayList<EnemyClass> type1Enemy;
    private ArrayList <ProjectileClass> playerBullet;
    private ArrayList<ProjectileClass> enemybullet;
    
    public TestLevel(int id)
    {
    
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        //init resources here
        bmap = new BlockMap("./res/secondDemoMap.tmx");
        mapX = 0;
        mapY = 0;
        isGameOver = false;
        isPause = false;
        startPos = new Vector2f(326, 150);
        player = new PlayerClass(startPos, 64, 80, .5f, 0);
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        Input input = gc.getInput();
        if(!isGameOver)
        {
            if(!isPause)
            {
                if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
                {
                    System.out.println("MouseX: " + input.getMouseX() + ", MouseY: " + input.getMouseY());
                }
                
                player.setControl(gc, delta, bmap);
            }
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        //calculate the offset of th
        if(!isGameOver)
        {
            if(!isPause)
            {
                bmap.tmap.render(0, 0);
                //temporary
                g.draw(player.getFootPoly());
            }
        }
    }
    
    public int getID()
    {
        return 0;
    }
}

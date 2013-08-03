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
import org.newdawn.slick.geom.Vector2f;

public class Level_01 extends BasicGameState {
    private PlayerClass player;
    private setupChar _char;
    private int score;
    private BlockMap bmap;
    boolean isPause;
    boolean isGameOver;
    public Level_01(int id)
    {
        
    }
    
    public Level_01(int score, PlayerClass p)
    {
        this.player = p;
        player.setVector(new Vector2f(0, 600));
        this.score = score;
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
         bmap = new BlockMap("./res/Level_01.tmx");  
         _char = new setupChar(0);
         this.player = new PlayerClass(new Vector2f(64, 600), 64, 80, .25f, .14f);
    }
    
    @Override
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
    
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        float mapX = bmap.tmap.getTileWidth() - player.getVector().getX();
        float mapY = bmap.tmap.getTileHeight() - player.getVector().getY() + 650;
        
        g.translate(mapX, mapY);
        bmap.tmap.render(0, 0);
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

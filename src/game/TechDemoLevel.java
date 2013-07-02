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
import com.Enjyn.Camera;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;

public class TechDemoLevel extends BasicGameState {
    
    private BlockMap bMap;
    private PlayerClass player;
    private Vector2f startPos;
    private float mapX, mapY;

    public TechDemoLevel(int id)
    {
    
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        startPos = new Vector2f(100, 650);
        bMap = new BlockMap("./res/demoMap.tmx");
        player = new PlayerClass(startPos, 64, 64, .25f, 0);
        mapX = 0;
        mapY = 0;

    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        player.setControl(gc, delta, bMap);     
        for(int i = 0; i < bMap.entities.size(); i++)
        {
            Block tile = (Block) bMap.entities.get(i);
            if(player.getKeyPressed() && gc.getInput().isKeyDown(Input.KEY_D))
            {
                mapX -= (float)0.001 * delta;
                tile.poly.setX(mapX);
            }
            
            if(player.getKeyPressed() && gc.getInput().isKeyDown(Input.KEY_A));
            {
                mapX += (float)0.001 * delta;
                tile.poly.setX(mapX);
            }
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        bMap.tmap.render((int)mapX, (int)mapY);
        g.draw(player.getPolygon());
    }
    
    public int getID()
    {
        return 0;
    }
}

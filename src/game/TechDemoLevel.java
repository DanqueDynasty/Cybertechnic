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
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Shape;
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
        if(gc.getInput().isKeyDown(Input.KEY_D))
        {
            if(player.getKeyPressed())
            {
                mapX -= 0.05f * delta;
            }
        }
        
        if(gc.getInput().isKeyDown(Input.KEY_A))
        {
            mapX += 0.05f * delta;
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
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
        g.setColor(Color.yellow);
        g.draw(player.groundPoly);
        g.resetTransform();
    }
    
    public int getID()
    {
        return 0;
    }
}

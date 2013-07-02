package game;

/**
 *
 * @author Nathan
 * Primary level for testing and debugging future concepts
 */

//imports

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
    private Camera cam;
    public TechDemoLevel(int id)
    {
    
    }
    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        startPos = new Vector2f(100, 650);
        bMap = new BlockMap("./res/demoMap.tmx");
        player = new PlayerClass(startPos, 64, 64, .25f, 0);
        cam = new Camera(gc);
    }
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        player.setControl(gc, delta, bMap);
        cam.update(gc, delta, bMap);
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
    {
        cam.Render(g);
        bMap.tmap.render(0, 0);
        g.draw(player.getPolygon());
    }
    
    public int getID()
    {
        return 0;
    }
}

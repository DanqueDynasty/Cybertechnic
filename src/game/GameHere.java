/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 */
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class GameHere extends StateBasedGame{
    public static final String GAME_NAME = "CyberTechnic----Build 3";
    public static final int DEMO_LEVEL = 0;
    
    public GameHere(String name)
    {
        super(name);
        this.addState(new TechDemoLevel(DEMO_LEVEL));
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(DEMO_LEVEL).init(gc, this);
        this.enterState(DEMO_LEVEL);
    }
    
    public static void main(String [] args)
    {
        try{
            AppGameContainer app = new AppGameContainer(new GameHere(GAME_NAME));
            app.setDisplayMode(1024, 768, false);
            app.setVSync(true);
            app.setTargetFrameRate(60);
            app.setShowFPS(false);
            app.start();
        }catch(SlickException e)
        {
            e.printStackTrace();
        }
    }
}

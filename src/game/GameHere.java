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
    public static final String GAME_NAME = "RaveWave---Build 96";
    //public static final int DEMO_LEVEL = 0;
    public static final int MENU_LEVEL = 0;
    public static final int CHARCONFIG_SREEN = 1;
    public static final int LEVEL_01 = 2;
    public static final int LEVEL_02 = 3;
    
    public GameHere(String name)
    {
        super(name);
        //this.addState(new TechDemoLevel(DEMO_LEVEL));
      //  this.addState(new TestLevel(DEMO_LEVEL));
        this.addState(new HomeScreen(MENU_LEVEL));
        this.addState(new setupChar(CHARCONFIG_SREEN));
        this.addState(new Level_01(LEVEL_01));
        this.addState(new Level_02(LEVEL_02));
    }
    
    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //this.getState(DEMO_LEVEL).init(gc, this);
        //this.getState(DEMO_LEVEL).init(gc, this);
        //this.enterState(DEMO_LEVEL);
        this.getState(MENU_LEVEL).init(gc, this);
        this.getState(CHARCONFIG_SREEN).init(gc, this);
        this.getState(LEVEL_01).init(gc, this);
        this.getState(LEVEL_02).init(gc, this);
        this.enterState(MENU_LEVEL);
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

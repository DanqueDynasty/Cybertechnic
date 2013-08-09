/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 * 
 * class responsible for choosing the type of character
 * 
 */

import com.Enjyn.PlayerClass;
import com.Enjyn.ProjectileClass;
import java.util.ArrayList;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.geom.Vector2f;

public class setupChar extends BasicGameState {
    private static PlayerClass player;
    private Image selDarhyl;
    private Image selRose;
    private Image startImage;
    private float time;
    private float timeOfLastFrameChange;
    private int currentFrame;
    private int totalFrame;
    private SpriteSheet masterSprite;
    private Image masterImage;
    private Level_01 lvl01;
    private boolean ready;
    private SpriteSheet darhySpriteSheet;
    private ArrayList<ProjectileClass> playerBullet;
    
    public setupChar(int id)
    {
    
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        player = new PlayerClass(new Vector2f(100, 100), 64, 80, 0.5f, 0.25f);
        player.setHealth(100);
        player.setWeaponID(0);
        player.setDirection(1); 
        darhySpriteSheet = new SpriteSheet("./res/masterSprite_Darhyl.png", 64, 80);
        
        
        selDarhyl = new Image("./res/darhylChoose_inactive.png");
        selRose = new Image("./res/roseChoose_inactive.png");
        startImage = new Image("./res/starBtn_unactive.png");
        
        ready = false;
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        Input input = gc.getInput();
        //start with the gender button for Darhyl
        if(input.getMouseX() >= 192 && input.getMouseX() <= (192 + selDarhyl.getWidth()) && input.getMouseY() >= 512 && input.getMouseY() <= (512 + selDarhyl.getHeight()))
        {
            if(ready == false)
            {
                if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                {
                    ready = true;
                    player.setGender(0);
                    player.setupSpriteSheet(darhySpriteSheet);
                }
            }
        }
        //Select rose
        if(input.getMouseX() >= 576 && input.getMouseX() <= (576 + selRose.getWidth()) && input.getMouseY() >= 512 && input.getMouseY() <= (512 + selRose.getHeight()))
        {
            if(ready == false)
            {
                if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                {
                    ready = true;
                    player.setGender(1);
                }
            }
        }
        
        //start button
        if(input.getMouseX() >= 384 && input.getMouseX() <= (384 + startImage.getWidth()) && input.getMouseY() >= 586 && input.getMouseY() <= (586 + startImage.getHeight()))
        {
            if(ready == true)
            {
                if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
                {              
                    sbg.enterState(2);
                    Level_01 lvl = new Level_01(0, player, playerBullet);
                }
            }
        }
        
    }
    
    public PlayerClass getPlayer()
    {
        return player;
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        g.drawImage(selDarhyl, 192, 512);
        g.drawImage(startImage, 384, 586);
        //g.drawImage(selRose, 576, 512);
                
    }
    
    public void setReady(boolean ready)
    {
        this.ready = ready;
    }
    
    public int getID()
    {
        return 1;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Nathan
 * 
 * this screen is responsible for 
 */

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.BasicGameState;

public class HomeScreen extends BasicGameState{
    
    private Image startImage;
    private Image configImage;
    private Image helpImage;
    private Image quitImage;
    
    public HomeScreen(int id)
    {
    
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException
    {
        startImage = new Image("./res/starBtn_unactive.png");
        configImage = new Image("./res/configBtn_unactive.png");
        helpImage = new Image("./res/helpBtn_unactive.png");
        quitImage = new Image("./res/quitBtn_unactive.png");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException
    {
        Input input = gc.getInput();
        //check if in the start button
        if(input.getMouseX() >= 64 && input.getMouseX() <= 64 + startImage.getWidth() && input.getMouseY() >= 128 && input.getMouseY() <= 128 + startImage.getHeight())
        {
            if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
            {
                sbg.enterState(1);
            }
        }
        //config screen
        if(input.getMouseX() >= 64 && input.getMouseX() <= 64 + configImage.getWidth() && input.getMouseY() >= 202 && input.getMouseY() <= 202 + configImage.getHeight())
        {
            if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
            {
                
            }
        }
        
        
        
        //handle the quit button
        if(input.getMouseX()  >= 64 && input.getMouseX() <= 64 + quitImage.getWidth() && input.getMouseY() >= 202 && input.getMouseY() <= 350 + quitImage.getHeight())
        {
            if(input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
            {
                System.exit(0);
            }
        }
    }
    
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g)throws SlickException
    {
        g.drawImage(startImage, 64, 128);
        g.drawImage(configImage, 64, 202);
        g.drawImage(helpImage, 64, 276);
        g.drawImage(quitImage, 64, 350);
    }
    
    public int getID()
    {
        return 0;
    }
}

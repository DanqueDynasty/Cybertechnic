/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;
/**
 *
 * @author Nathan
 */

import org.newdawn.slick.*;

public class OverheadGuiClass {
    
    
    private Input input;
    public Sound music;
    private Image resumeImage;
    private Image menuImage;
    private Image volDownImage;
    private Image volUpImage;
    public boolean isPause;
    
    public OverheadGuiClass()
    {
    
    }
    
    //HUD stuff
    public void initHUD(GameContainer gc)throws SlickException
    {
    
    }
    
    public void updateHUD(GameContainer gc)throws SlickException
    {
    
    }
    
    public void renderHUD(GameContainer gc, Graphics g)throws SlickException
    {
    
    }
    
    //pause screen
    public void initPauseScreen(GameContainer gc)throws SlickException
    {
        //init the pause screen code
        resumeImage = new Image("./res/gui/resumeBtn_unactive.png");
        menuImage = new Image("./res/gui/menuBtn_unactive.png");
        volDownImage = new Image("./res/gui/voldown_inactive.png");
        volUpImage = new Image("./res/gui/volUp_inactive.png");
    }
    
    public void setPauseStatus(boolean p)
    {
        isPause = p;
    }
    
    public void updatePauseScreen(GameContainer gc)throws SlickException
    {
        Input input = gc.getInput();
        if(input.getMouseX() <= 128 && input.getMouseX() >= 128 + resumeImage.getWidth() && input.getMouseY() >= 196 && input.getMouseY() <= 196 + resumeImage.getHeight())
        {
            if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON))
            {
                //adjust the value for the true or false
                this.setPauseStatus(false);
            }
        }
        
        
    }

    public void renderPauseScreen(GameContainer gc, Graphics g)throws SlickException
    {
        g.drawImage(resumeImage, 128, 196);
        g.drawImage(menuImage, 128, 270);
        g.drawImage(volDownImage, 256, 196);
        g.drawImage(volUpImage, 512, 196);
    }
    
    public boolean getPauseStatus()
    {
        return isPause;
    }
}

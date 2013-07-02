/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

/**
 *
 * @author Nathan
 */

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Input;
import com.Enjyn.BlockMap;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Graphics;

public class Camera {
    Rectangle viewPort;
    public Camera(GameContainer gc)throws SlickException
    {
        viewPort = new Rectangle(0, 0, gc.getWidth(), gc.getHeight());
    }
    
    public void update(GameContainer gc, int delta, BlockMap bmap)throws SlickException
    {
        Vector2f trans = new Vector2f(0,0);
        
        Input input = gc.getInput();
        
        if(input.isKeyDown(Input.KEY_W))
        {
            trans.y -= 0.5f * delta;
        }
        
        if(input.isKeyDown(Input.KEY_S))
        {
            trans.x -= 0.5f * delta;
        }
            
        int mapWidth = bmap.tmap.getWidth() * bmap.tmap.getTileWidth();
        int mapHeight = bmap.tmap.getHeight() * bmap.tmap.getTileHeight();
        
        if(viewPort.getX() + trans.x < 0)
        {
            viewPort.setX(0);
        }else if(viewPort.getX() + trans.x + gc.getWidth() > mapWidth){
            viewPort.setX(mapWidth - gc.getWidth());
        }else{
            viewPort.setX(viewPort.getX() + trans.x);
        }
        
        if(viewPort.getY() + trans.y < 0)
        {
            viewPort.setY(0);
        }else if(viewPort.getY() + trans.y + gc.getHeight() > mapHeight){
            viewPort.setY(mapHeight - gc.getHeight());
        }else{
            viewPort.setY(viewPort.getY() + trans.y);
        }
    }
    
    public void Render(Graphics g)
    {
        g.translate(-viewPort.getX(), -viewPort.getY());
    }
}

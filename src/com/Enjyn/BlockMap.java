/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

/**
 *
 * @author Nathan
 */

import java.util.ArrayList;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class BlockMap {
    //initialize dem variables
    public TiledMap tmap;
    public int mapWidth;
    public int mapHeight;
    
    //set this asside for specifying the tile
    public static int square[] = {1, 1, 15, 1, 15, 15, 1, 15};
    public static int groundSquare[] = {1, 1, 15, 1, 15, 15, 1, 15};
    
    public ArrayList<Object> entities;
    
    public BlockMap(String ref)throws SlickException{
        entities = new ArrayList<>();
        tmap = new TiledMap(ref, "res");
        mapWidth = tmap.getWidth() * tmap.getTileWidth();
        mapHeight = tmap.getHeight() * tmap.getTileHeight();
        
        for(int x = 0; x < tmap.getWidth(); ++x)
        {
            for(int y = 0; y < tmap.getHeight(); ++y)
            {
                int tileID = tmap.getTileId(x, y, 0);
                if(tileID == 1)
                {
                    entities.add(new Block(x * 16, y * 16, square, "square"));
                }
                
            }
        }
    }
}

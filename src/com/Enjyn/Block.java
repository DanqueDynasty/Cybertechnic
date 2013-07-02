/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

/**
 *
 * @author Nathan
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class Block {
    public Polygon poly;
    
    public Block(int x, int y, int[] square, String type)
    {
        poly = new Polygon(new float[]
        {
            x + square[0], y + square[1],
            x + square[2], y + square[3],
            x + square[4], y + square[5],
            x + square[6], y + square[7]
        });
    }
    
    public void update(int delta)
    {
    
    }
    
    public void render(Graphics g)
    {
        g.draw(poly);
    }
}

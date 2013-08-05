/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author John
 */
public class Weapon {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public void use(float x, float y){
        
    }
    public void setDirection(int direction){
        this.direction = direction;
    }
    public void update(int delta){
        
    }
    public void render(Graphics g){
        
    }
    public Rectangle getRect(){
        return null;
    }
    protected int direction;
}

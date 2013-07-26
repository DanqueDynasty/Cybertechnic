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
public class Bullet {
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final float SPEED = 0.3f;
    public Bullet(float x, float y, int direction){
        this.x = x;
        this.y = y;
        this.direction = direction;
        setRect(x,y,20,20);
    }
    public void update(int delta){
        if(direction==LEFT){
            setX(getX()-(SPEED*delta));
        }else if(direction==RIGHT){
            setX(getX()+(SPEED*delta));
        }
    }
    public void render(Graphics g){
        g.draw(getRect());
    }
    public void setX(float x){
        this.x = x;
        setRect(x,y,20,20);
    }
    public float getX(){
        return x;
    }
    public void setY(float y){
        this.y = y;
        setRect(x,y,20,20);
    }
    public float getY(){
        return y;
    }
    public void setRect(float x, float y, float h, float w){
        rect = new Rectangle(x,y,h,w);
    }
    public Rectangle getRect(){
        return rect;
    }
    private int direction;
    private float x;
    private float y;
    private Rectangle rect;
}

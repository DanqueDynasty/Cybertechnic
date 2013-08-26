/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author John
 */
public class Gun extends Weapon{
    public Gun(){
        bullet = new ArrayList<Bullet>();
    }
    @Override
    public void use(float x, float y){
        bullet.add(new Bullet(x,y,direction));
    }
    @Override
    public void update(int delta){
        for(int i = 0;i<bullet.size();i++){
            bullet.get(i).update(delta);
        }
    }
    @Override
    public void render(Graphics g){
        for(int i = 0;i<bullet.size();i++){
            bullet.get(i).render(g);
        }
    }
    @Override
    public Rectangle getRect(){
        return rect;
    }
    public Rectangle getProjectile(int i){
        return bullet.get(i).getRect();
    }
    
    public int getBulletSize()
    {
        return bullet.size();
    }
    
    public ArrayList<Bullet> getBulletArray()
    {
        return bullet;
    }
    
    private Rectangle rect;
    private ArrayList<Bullet> bullet;
}

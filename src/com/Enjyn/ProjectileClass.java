/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class ProjectileClass implements SwingEntityFramework {
    
    public Vector2f posVec;
    public float wOffset;
    public float hOffset;
    public Polygon poly;
    public float speed;
    
    public ProjectileClass()
    {
    
    }

    public void setVector(Vector2f vec) {
        posVec = vec;
    }

    @Override
    public void setWidthOffset(float w) {
        wOffset = w;
    }

    @Override
    public void setHeightOffset(float h) {
        hOffset = h;
    }

    @Override
    public void setupPolygon(Vector2f vec, float w, float h) {
        poly = new Polygon(new float[]{
            vec.x, vec.y,
            vec.x, vec.y + h,
            vec.x + w, vec.y + h,
            vec.x + w, vec.y
        });
    }

    public void setSpeed(float s)
    {
        speed = s;
    }
    
    @Override
    public Vector2f getVector() {
        return posVec;
    }

    @Override
    public float getWidthOffset() {
        return wOffset;
    }

    @Override
    public float getHeightOffset() {
        return hOffset;
    }

    @Override
    public Polygon getPolygon() {
        return poly;
    }
    
    public void update(int delta)
    {
    
    }
    
    public void playerFired(boolean hasFired, PlayerClass player)
    {
        hasFired = player.getFiredStatus();
        if(hasFired)
        {
            switch(player.getDirection())
            {
                case 0:
                    
                    break;
                case 1:
                    
                    break;
                default:
                    break;
            }
        }
    }
    
    public void enemyFired_bullet(boolean hasFired, EnemyClass enem, PlayerClass player, int delta)
    {
        hasFired = enem.getFiredStatus();
        if(hasFired)
        {
            switch(enem.getDirection())
            {
                case 0:
                    this.posVec.x -= speed * delta;
                    this.poly.setX(posVec.x);
                case 1:
                    this.posVec.x += speed * delta;
                    this.poly.setX(posVec.x);
                    break;
                default:
                    break;
            }
        }
    }
    
    public void enemyFired_Swing(boolean hasFired, EnemyClass enem, PlayerClass player, int delta)
    {
        hasFired = enem.getFiredStatus();
        
        
    }
}

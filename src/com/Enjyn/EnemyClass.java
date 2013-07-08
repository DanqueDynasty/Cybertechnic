/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Nathan
 */
public class EnemyClass implements SwingEntityFramework{
    
    public Vector2f posVec;
    public float wOffset;
    public float hOffset;
    public Polygon poly;
    public Polygon groundPoly;
    public Polygon viewPoly; // if player has intersected
    public float speed;
    public boolean isJumping;
    
    public EnemyClass()
    {
    
    }

    @Override
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
        poly = new Polygon(new float[] {
            vec.x, vec.y,
            vec.x, vec.y + hOffset,
            vec.x + wOffset, vec.y + hOffset,
            vec.x + wOffset, vec.y
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
    
    public boolean isPlayerFound(PlayerClass player)
    {
        if(player.getPolygon().intersects(viewPoly))
        {
            return true;
        }
        return false;
    }
    
    
    
}

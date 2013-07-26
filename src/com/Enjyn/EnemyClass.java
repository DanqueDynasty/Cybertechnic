/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import java.util.ArrayList;
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
    public float velocityF;
    public int direction;
    public int type;
    public float health;
    public float posOffset;//will manage the position offset of the character, so no run and gun
    public float distanceOffset;
    public boolean hasFired;
    public boolean activeFire;
    public ProjectileClass bullet;
    public float ctime;
    public float timeSinceLastChange;
    public static final int LEFT = 0;
   public static final int RIGHT = 1;
    
    
    public EnemyClass(Vector2f vec, float w, float h, float s, float v, int t)
    {
        setVector(vec);
        setWidthOffset(w);
        setHeightOffset(h);
        setSpeed(s);
        setVelocityF(v);
        setupPolygon(vec, w, h);
        setupFootPoly(vec, w, h);
        setupViewPoly(vec, w, h);
        bullet = new ProjectileClass(vec, 16, 16);
        bullet.setSpeed(.5f);
        ctime = 0; 
        timeSinceLastChange = 0;
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
    
    public void setupFootPoly(Vector2f vec, float w, float h)
    {
        groundPoly = new Polygon(new float[] {
                vec.x, vec.y - 2,
                vec.x, vec.y + h,
                vec.x + w, vec.y + h,
                vec.x + w, vec.y - 2
        });
    }
    
    public void setupViewPoly(Vector2f vec, float w, float h)
    {
        //this function will be responsible
        viewPoly = new Polygon(new float[]{
            vec.x - posOffset, vec.y,
            vec.x - posOffset, vec.y + h,
            vec.x + w + posOffset, vec.y + h,
            vec.x + w + posOffset, vec.y
        });
    }
    
    public void setSpeed(float s)
    {
        speed = s;
    }

    public void setVelocityF(float v)
    {
        velocityF = v;
    }
    
    public void setDisOffset(float dis)
    {
        distanceOffset = dis;
    }
    
    public void setDirection(int d)
    {
        direction = d;
    }
    
    public void setType(int t)
    {
        type = t;
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public void setFired(boolean fir)
    {
        hasFired = fir;
    }   
    
    public void setActiveFire(boolean af)
    {
        activeFire = af;
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
    
    public int getDirection()
    {
        return direction;
    }
    
    public void setPosOffset(float p)
    {
        posOffset = p;
        this.setupViewPoly(posVec, this.getWidthOffset(), this.getHeightOffset());
    }
    
    public boolean isPlayerFound(PlayerClass player)
    {
        if(viewPoly.intersects(player.getPolygon()))
        {
            return true;
        }
        return false;
    }
    
    public boolean isOnGround(BlockMap bmap)
    {
        for(int i = 0; i < bmap.entities.size(); i++)
        {
            Block tile = (Block) bmap.entities.get(i);
            if(groundPoly.intersects(tile.poly))
                return true;
        }
        return false;
    }
    
    public boolean getFiredStatus()
    {
        return hasFired;
    }
    
    public boolean getActiveFire()
    {
        return activeFire;
    }
    
    public int getType()
    {
        return type;
    }
    
    public void update(PlayerClass player,BlockMap bmap, int delta)
    {
            //code here for how enemy will behave
        
        //first we check if the entity is on the ground
        if(!isOnGround(bmap))
        {
            posVec.y += (float)0.2 * delta;
            poly.setY(posVec.y);
            groundPoly.setY(posVec.y);
            viewPoly.setY(posVec.y);
        }
        
        if(isOnGround(bmap))
        {
            posVec.y += (float)0 * delta;
            poly.setY(posVec.y);
            groundPoly.setY(posVec.y);
            viewPoly.setY(posVec.y);
        }
        
        if(isPlayerFound(player))
        {
            //here we will compare the x and y coordinates
            if(player.getVector().getX() + distanceOffset > posVec.getX() && isOnGround(bmap))
            {
                posVec.x += speed * delta;
                poly.setX(posVec.x);
                groundPoly.setX(posVec.x);
                viewPoly.setX(posVec.x - posOffset);
                //set direction to head left
                setDirection(0);
                //set hasFired;
                
            }
            
            if(player.getVector().getX() - distanceOffset < posVec.getX() && isOnGround(bmap))
            {
                posVec.x -= speed * delta;
                poly.setX(posVec.x);
                groundPoly.setX(posVec.x);
                viewPoly.setX(posVec.x - posOffset);
                //set direction to go right
                setDirection(1);
            }
            
            ctime +=(float)(delta)/1000;
            
            if( ctime > timeSinceLastChange + 2)
            {
                setFired(true);
                ctime = 0;
            }
            if(hasFired)
            {
                bullet.addBullet_Enemy(this);
                setFired(false);
            }
            
            //
            activeFire = bullet.getActiveStatus();
            switch(getType())
            {
                case 0:
                    bullet.enemyFired_bullet(hasFired, this, delta);
                    break;
                case 1:
                    bullet.enemyFired_Swing(hasFired, this, delta);
                    break;
                default:
                    break;
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;

/**
 *
 * @author Nathan
 * This class is designed to handle the 
 */
public class PlayerClass implements SwingEntityFramework {
    
    public Vector2f playerVec;
    public Vector2f velocityVec;
    public float wOffset;
    public float hOffset;
    public Polygon poly;
    public Polygon groundPoly;
    public float speed;
    public float velocityF;
    public boolean keyPressed;
    public boolean isJumping;
    public int health;
    public int dir;
    public int gen;
    public SpriteSheet masterSprite;
    public Image masterImage;
    public float time;
    public float timeOfLastFrameChage;
    public int currentFrame;
    public int totalFrame;
    public int weapon;
    public boolean hasFired;
    public ProjectileClass bullet;
    
    public PlayerClass(Vector2f vec, float w, float h, float s, float v)
    {
        setVector(vec);
        setWidthOffset(w);
        setHeightOffset(h);
        setSpeed(s);
        setVelocityf(v);
        setupPolygon(vec, w, h);
        setupFootpoly(vec, w, h);
        bullet = new ProjectileClass(vec, w, h);
        hasFired = false;
        isJumping = false;
    }

    @Override
    public void setVector(Vector2f vec) {
        playerVec = vec;
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
    
    public void setupFootpoly(Vector2f vec, float w, float h)
    {
        groundPoly = new Polygon(new float[]
        {
            vec.x, vec.y - 2,
            vec.x, vec.y + hOffset,
            vec.x + wOffset, vec.y + hOffset,
            vec.x + wOffset, vec.y -2
        });
    }
    
    public void setSpeed(float s)
    {
        speed = s;
    }   
    
    public void setVelocityf(float v)
    {
        velocityF = v;
    }
    
    public void setVelocity(Vector2f v)
    {
        velocityVec = v;
    }
    
    public void setKeyPressed(boolean b)
    {
        keyPressed = b;
    }
    
    public void setDirection(int d)
    {
        dir = d;
    }
    
    public void setGender(int g)
    {
        gen = g;
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public void setWeapon(int w)
    {
        weapon = w;
    }
    
    public void setFired(boolean ba)
    {
        hasFired = ba;
    }
    
    public void setupSpriteSheet(SpriteSheet sprite)
    {
        int d = getDirection();
        masterSprite = sprite;
        masterImage = masterSprite.getSprite(currentFrame, d);
        totalFrame = 4;
    }
    
    public void updateSpriteSheet(int delta)
    {
        //I suppose this will go into the update method
        time += (float)delta/1000;
        if(time > timeOfLastFrameChage + 0.1f)
        {
            timeOfLastFrameChage = time;
            nextFrame();
        }
    }
    
    public void nextFrame()
    {
        currentFrame++;
        if(currentFrame < totalFrame - 1)
        {
            currentFrame = 0;
        }
        int d = getDirection();
        masterImage = masterSprite.getSprite(currentFrame, d);
    }
    
    public void setControl(GameContainer gc, int delta, BlockMap bmap)
    {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_A))
        {
            playerVec.x -= speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            setDirection(0);
            setKeyPressed(true);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x += speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
                setDirection(1);
            }
        }else{
            setKeyPressed(false);
        }
        
        if(input.isKeyDown(Input.KEY_D))
        {
            playerVec.x += speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            setKeyPressed(true);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x -= speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
            }
        }else{
            setKeyPressed(false);
        }
        
        if(input.isKeyPressed(Input.KEY_ENTER) && isOnGround(bmap))
        {
            setFired(true);
            
        }

        if(input.isKeyPressed(Input.KEY_SPACE) && isOnGround(bmap))
        {
            isJumping = true;
            setVelocityf((float)-5 * delta);
        }
        
        if(isJumping)
        {
            this.velocityF += 1 * delta;
            playerVec.y += this.velocityF;
            poly.setY((int)playerVec.y);
            groundPoly.setY((int)playerVec.y);
        }
        
        if(!isOnGround(bmap))
        {
            playerVec.y += (float)0.2 * delta;
            poly.setY((int)playerVec.y);
            groundPoly.setY((int)playerVec.y);
            System.out.println(isOnGround(bmap));
            isJumping = false;
        }
        
        if(isOnGround(bmap))
        {
            playerVec.y += (float)0 * delta;
            poly.setY((int)playerVec.y);
            groundPoly.setY((int)playerVec.y);
        }
        
        if(hasFired)
        { 
            bullet.addBullet_Player(this);
            setFired(false);
        }
        
        //switch weapon method
        switch(getWeapon())
        {
            case 1:
                bullet.playerFired(hasFired, this, delta);
                break;
            default:
                break;
        }
    }
    @Override
    public Vector2f getVector() {
        return playerVec;
    }

    @Override
    public float getWidthOffset() {
        return wOffset;
    }

    @Override
    public float getHeightOffset() {
        return hOffset;
    }
    
    public float getSpeed()
    {
        return speed;
    }

    @Override
    public Polygon getPolygon() {
        return poly;
    }
    
    public Polygon getFootPoly()
    {
        return groundPoly;
    }
    
    public float getVelocityf()
    {
        return velocityF;
    }
    
    public int getDirection()
    {
        return dir;
    }
    
    public int getGender()
    {
        return gen;
    }
    
    public int getWeapon()
    {
        return weapon;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public Image getMasterImage()
    {
        return masterImage;
    }
    
    public boolean getFiredStatus()
    {
        return hasFired;
    }
    
    public SpriteSheet getMasterSprite()
    {
        return masterSprite;
    }
    
    public boolean collidedWithTile(BlockMap bmap)
    {
        for(int i = 0; i < bmap.entities.size(); ++i)
        {
            Block tile = (Block) bmap.entities.get(i);
            if(poly.intersects(tile.poly))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isOnGround(BlockMap bmap)
    {
        for(int i = 0; i < bmap.entities.size(); i++)
        {
            Block tile = (Block) bmap.entities.get(i);
            {
                if(groundPoly.intersects(tile.poly))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean getKeyPressed()
    {
        return keyPressed;
    }
}

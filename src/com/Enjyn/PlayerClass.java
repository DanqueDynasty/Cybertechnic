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
    public Polygon ceilingPoly;
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
    public boolean activeFire;
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
        setupCeilingPoly(vec, w, h);
        bullet = new ProjectileClass(vec, 16, 16);
        bullet.setSpeed(.5f);
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
            vec.x, vec.y + h - 3,
            vec.x, vec.y + hOffset,
            vec.x + wOffset, vec.y + hOffset,
            vec.x + wOffset, vec.y + h - 3
        });
    }
    
    public void setupCeilingPoly(Vector2f vec, float w, float h)
    {
        ceilingPoly = new Polygon(new float[]{
            vec.x, vec.y,
            vec.x, vec.y - 3,
            vec.x + w, vec.y - 3,
            vec.x + w, vec.y
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
    
    public void setJumpStatus(boolean j)
    {
        isJumping = j;
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
            ceilingPoly.setX(playerVec.x);
            setDirection(0);
            setKeyPressed(true);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x += speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
                ceilingPoly.setX(playerVec.x);
            }
        }else{
            setKeyPressed(false);
        }
        
        if(input.isKeyDown(Input.KEY_D))
        {
            playerVec.x += speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            ceilingPoly.setX(playerVec.x);
            setKeyPressed(true);
            setDirection(1);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x -= speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
                ceilingPoly.setX(playerVec.x);
            }
        }else{
            setKeyPressed(false);
        }
        
        if(input.isKeyPressed(Input.KEY_ENTER) && isOnGround(bmap))
        {
            setFired(true); 
        }

        if(input.isKeyPressed(Input.KEY_SPACE) && isOnGround(bmap) && !isCeilingTouched(bmap))
        {
            setJumpStatus(true);
            setVelocityf((float)-6 * delta);
        }
        
        if(input.isKeyPressed(Input.KEY_1))
        {
            setWeapon(1);
        }
        
        if(input.isKeyPressed(Input.KEY_2))
        {
            setWeapon(2);
        }
        
        if(getJumpStatus() == true)
        {
            this.velocityF -= 1 * delta;
            playerVec.y += this.velocityF;
            float offVec = playerVec.getY() + hOffset;
            poly.setY((int)playerVec.y);
            groundPoly.setY(offVec);
            ceilingPoly.setY(playerVec.y);
        }
        
        if(!isOnGround(bmap))
        {
            playerVec.y += (float)0.2 * delta;
            float offVec = playerVec.getY() + hOffset;
            poly.setY((int)playerVec.y);
            groundPoly.setY(offVec);
            ceilingPoly.setY(playerVec.y);
            //System.out.println(isOnGround(bmap));
            setJumpStatus(false);
        }
        
        if(isOnGround(bmap))
        {
            for(int i = 0; i < bmap.entities.size(); i++)
            {
                Block tile = (Block)bmap.entities.get(i);
                if((int)playerVec.getY() <= (int)tile.poly.getY())
                {
                    playerVec.y += (float)0 * delta;
                    float offVec = playerVec.getY() + hOffset;
                    poly.setY((int)playerVec.y);
                    groundPoly.setY(offVec);
                    ceilingPoly.setY(playerVec.y);
                }
            }          
        }
        
        if(isCeilingTouched(bmap))
        {
            setJumpStatus(false);
            playerVec.y += (float)0.2 * delta;
            float offVec = playerVec.getY() + hOffset;
            poly.setY(playerVec.y);
            groundPoly.setY(offVec);
            ceilingPoly.setY(playerVec.y);
        }
        
        if(hasFired)
        { 
            bullet.addBullet_Player(this);
            setFired(false);
        }
        
        //switch weapon method
        activeFire = bullet.getActiveStatus();
        switch(getWeapon())
        {
            case 1:
                bullet.playerFired(activeFire, this, delta);
                break;
            case 2:
                bullet.playerSwing(this, delta);
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
    
    public Polygon getCeilingPoly()
    {
        return ceilingPoly;
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

        
        /*
        for(int i = 0; i < bmap.entities.size(); i++)
        {
            Block tile = (Block) bmap.entities.get(i);
            if((int)(groundPoly.getY()) == (int)tile.poly.getY())
            {
                return true;
            }
        }
        return false;
        */
    }
    
    public boolean isCeilingTouched(BlockMap bmap)
    {
        for(int i = 0; i < bmap.entities.size(); i++)
        {
            Block tile = (Block)bmap.entities.get(i);
            if(ceilingPoly.intersects(tile.poly))
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean getKeyPressed()
    {
        return keyPressed;
    }
    
    public boolean getJumpStatus()
    {
        return isJumping;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;

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
    
    public PlayerClass(Vector2f vec, float w, float h, float s, float v)
    {
        setVector(vec);
        setWidthOffset(w);
        setHeightOffset(h);
        setSpeed(s);
        setVelocityf(v);
        setupPolygon(vec, w, h);
        setupFootpoly(vec, w, h);
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
            playerVec.x, playerVec.y,
            playerVec.x, playerVec.y + hOffset,
            playerVec.x + wOffset, playerVec.y + hOffset,
            playerVec.x + wOffset, playerVec.y
        });
    }
    
    public void setupFootpoly(Vector2f vec, float w, float h)
    {
        groundPoly = new Polygon(new float[]
        {
            vec.x, vec.y,
            vec.x, vec.y + hOffset - 5,
            vec.x + wOffset, vec.y + hOffset - 5,
            vec.x + wOffset, vec.y
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
    
    public void setControl(GameContainer gc, int delta, BlockMap bmap)
    {
        Input input = gc.getInput();
        if(input.isKeyDown(Input.KEY_A))
        {
            playerVec.x -= speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            setKeyPressed(true);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x += speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
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

        if(input.isKeyPressed(Input.KEY_SPACE) && isOnGround(bmap))
        {
            isJumping = true;
            setVelocityf((float)-5 * delta);
        }
        
        if(isJumping)
        {
            this.velocityF += 1 * delta;
            playerVec.y += this.velocityF;
            poly.setY(playerVec.y);
            groundPoly.setY(playerVec.y);
        }
        
        if(!isOnGround(bmap))
        {
            playerVec.y += (float)0.5 * delta;
            poly.setY(playerVec.y);
            groundPoly.setY(playerVec.y);
            System.out.println(isOnGround(bmap));
            isJumping = false;
        }
        
        if(isOnGround(bmap))
        {
            playerVec.y += (float)0 * delta;
            poly.setY(playerVec.y);
            groundPoly.setY(playerVec.y);
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

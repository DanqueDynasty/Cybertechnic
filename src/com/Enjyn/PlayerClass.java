/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import java.util.ArrayList;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Input;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;

/**
 *
 * @author Nathan
 * This class is designed to handle the 
 */
public class PlayerClass implements SwingEntityFramework {
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    
    
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
    public int row;
    public SpriteSheet masterSprite;
    public Image masterImage;
    public float time;
    public float timeOfLastFrameChage;
    public int currentFrame;
    public int totalFrame;
    public int weaponID;    //used to be "weapon", changed so that "weapon" could be the name of the actual weapon arraylist
    public boolean hasFired;
    public boolean activeFire;
    public ProjectileClass bullet;
    
    private ArrayList<Weapon> weapon;
    
    
    
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
        hasFired = false;
        isJumping = false;
        weapon = new ArrayList<Weapon>();
        weapon.add(new Gun());
        totalFrame = 4;
        currentFrame = 0;
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
        weapon.get(getWeaponID()).setDirection(d);
    }
    
    public void setGender(int g)
    {
        gen = g;
    }
    
    public void setHealth(int h)
    {
        health = h;
    }
    
    public void setWeaponID(int w)
    {
        weaponID = w;
    }
    
    public void setJumpStatus(boolean j)
    {
        isJumping = j;
    }
    
    public void setFired(boolean ba)
    {
        hasFired = ba;
    }
    
    public void setTotalFrames(int f)
    {
        totalFrame = f;
    }
    
    public void setRow(int r)
    {
        row = r;
    }
    
    public void setupSpriteSheet(SpriteSheet sprite)
    {
        //int d = getDirection();
        int d = getRow();
        masterSprite = sprite;
        masterImage = masterSprite.getSprite(currentFrame, d);
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
        if(currentFrame > getTotalFrames() - 1)
        {
            currentFrame = 0;
        }
        int d = getRow();
        masterImage = masterSprite.getSprite(currentFrame, d);
    }
    public void update(int delta){
        weapon.get(getWeaponID()).update(delta);
    }
    public void render(Graphics g){
        weapon.get(getWeaponID()).render(g);
    }
    
    public void handleDamage(EnemyClass enem)
    {
        for(int i = 0; i < enem.getWeapon().size(); i++)
        {
            if(enem.getGun().getBulletSize() == 0)
            {
            //do Nothing
            }
            else
            {
                if(enem.getGun().getProjectile(i).intersects(poly))
                {
                    int newHealth = this.getHealth() - 15;
                    setHealth(newHealth);
                }
            }
        }
    }
    
    public void setControl(GameContainer gc, int delta, BlockMap bmap)
    {
        Input input = gc.getInput();
        setTotalFrames(4);
        if(getDirection()==LEFT){
            setRow(1);
        }else{
            setRow(0);
        }
        if(input.isKeyDown(Input.KEY_A))
        {
            setDirection(LEFT);
            playerVec.x -= speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            ceilingPoly.setX(playerVec.x);
            //setDirection(0);
            setKeyPressed(true);
            setTotalFrames(8);
            setRow(3);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x += speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
                ceilingPoly.setX(playerVec.x);
            }
        }else{
            setKeyPressed(false);
            setTotalFrames(4);
        }
        
        if(input.isKeyDown(Input.KEY_D))
        {
            setDirection(RIGHT);
            playerVec.x += speed * delta;
            poly.setX(playerVec.x);
            groundPoly.setX(playerVec.x);
            ceilingPoly.setX(playerVec.x);
            setKeyPressed(true);
            setTotalFrames(8);
            setRow(2);
            //setDirection(1);
            if(collidedWithTile(bmap) == true)
            {
                playerVec.x -= speed * delta;
                poly.setX(playerVec.x);
                groundPoly.setX(playerVec.x);
                ceilingPoly.setX(playerVec.x);
            }
        }else{
            setKeyPressed(false);
            setTotalFrames(4);
        }
        
        System.out.println("CurrentFrame" + currentFrame);
        
        if(input.isKeyPressed(Input.KEY_ENTER) && isOnGround(bmap))
        {
           weapon.get(getWeaponID()).use(poly.getX(), poly.getY()); 
           // setFired(true); 
        }

        if(input.isKeyPressed(Input.KEY_SPACE) && isOnGround(bmap) && !isCeilingTouched(bmap))
        {
            setJumpStatus(true);
            setVelocityf((float)-3 * delta);
        }
        
        if(input.isKeyPressed(Input.KEY_1))
        {
            setWeaponID(0);
        }
        
        if(input.isKeyPressed(Input.KEY_2))
        {
            setWeaponID(1);
        }
        
        if(getJumpStatus() == true)
        {
            this.velocityF -= .5 * delta;
            playerVec.y += this.velocityF;
            float offVec = playerVec.getY() + hOffset;
            poly.setY((int)playerVec.y);
            groundPoly.setY(offVec);
            ceilingPoly.setY(playerVec.y);
            setTotalFrames(1);
            setRow(4);
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
            //setTotalFrames(1);
            //setRow(3);
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
            setJumpStatus(false);
        }
        
        if(isCeilingTouched(bmap))
        {
            setJumpStatus(false);
            playerVec.y += (float)1 * delta;
            float offVec = playerVec.getY() + hOffset;
            poly.setY(playerVec.y);
            groundPoly.setY(offVec);
            ceilingPoly.setY(playerVec.y);
        }
        /*
        if(hasFired)
        { 
            bullet.addBullet_Player(this);
            setFired(false);
        }
        
        //switch weapon method
        activeFire = bullet.getActiveStatus();
        switch(getWeaponID())
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
        * 
        */
        
        updateSpriteSheet(delta);
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
    
    public int getWeaponID()
    {
        return weaponID;
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
    
    public int getRow()
    {
        return row;
    }
    
    public int getTotalFrames()
    {
        return totalFrame;
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

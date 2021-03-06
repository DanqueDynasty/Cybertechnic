/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import java.util.ArrayList;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;

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
    public int weaponID;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public Gun gun;
    public int time;
    public int timeOfLastFrameChange;
    public int currentFrame;
    public int totalFrame;
    public int row;
    public SpriteSheet masterSprite;
    public Image masterImage;
    
    private ArrayList<Weapon> weapon;
    
    
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
        weapon = new ArrayList<Weapon>();
        gun = new Gun();
        weapon.add(gun);
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
        weapon.get(getWeaponID()).setDirection(d);
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
    
    public void setWeaponID(int w)
    {
        weaponID = w;
    }
    
    public void setupSpriteSheet(SpriteSheet sprite)
    {
        int r = this.getRow();
        masterSprite = sprite;
        masterImage = masterSprite.getSprite(currentFrame, r);
    }
    
    public void updateSpriteSheet(int delta)
    {
        time +=(float)delta/1000;
        if(time > timeOfLastFrameChange + 0.1f)
        {
            timeOfLastFrameChange = time;
        }
    }
    
    public void nextFrame()
    {
        currentFrame++;
        if(currentFrame > this.getTotalFrame() - 1 )
        {
            currentFrame = 0;
        }
        
        int r = getRow();
        masterImage = masterSprite.getSprite(currentFrame, r);
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
    
    public int getWeaponID()
    {
        return weaponID;
    }
    
    public void setPosOffset(float p)
    {
        posOffset = p;
        this.setupViewPoly(posVec, this.getWidthOffset(), this.getHeightOffset());
    }
    
    public void setTotalFrame(int t)
    {
        totalFrame = t;
    }
    
    public void setRow(int r)
    {
        row = r;
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
    
    public boolean isCollide(BlockMap bmap)
    {
        for(int i = 0; i < bmap.entities.size(); i++)
        {
            Block tile = (Block) bmap.entities.get(i);
            if(poly.intersects(tile.poly))
            {
                return true;
            }
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
    
    public int getTotalFrame()
    {
        return totalFrame;
    }
    
    public int getRow()
    {
        return row;
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
                setDirection(LEFT);
                //set hasFired;
                
            }
            
            if(player.getVector().getX() - distanceOffset < posVec.getX() && isOnGround(bmap))
            {
                posVec.x -= speed * delta;
                poly.setX(posVec.x);
                groundPoly.setX(posVec.x);
                viewPoly.setX(posVec.x - posOffset);
                //set direction to go right
                setDirection(RIGHT);
            }
            
            ctime +=(float)(delta)/1000;
            
            if( ctime > timeSinceLastChange + 2)
            {
                setFired(true);
                ctime = 0;
            }
            if(hasFired)
            {
                //bullet.addBullet_Enemy(this);
                weapon.get(weaponID).use(poly.getX(), poly.getY());
                setFired(false);
            }
            
            weapon.get(weaponID).update(delta);
            
        }
    }
    
    public void handleDamage(PlayerClass player)
    {
        for(int i = 0; i < player.getWeapon().size(); i++)
        {
            if(player.getGun().getBulletSize() == 0)
            {
            
            }
            else
            {
                if(player.getGun().getProjectile(i).intersects(poly))
                {
                    player.getGun().getBulletArray().remove(i);
                    health = health - 15;
                }
            }
        }
    }
    
    public void render(Graphics g)
    {
        weapon.get(getWeaponID()).render(g);
    }
    
    public ArrayList<Weapon> getWeapon()
    {
        return weapon;
    }
    
    public Gun getGun()
    {
        return gun;
    }
    
}

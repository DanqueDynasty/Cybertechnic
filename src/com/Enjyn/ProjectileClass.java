/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.GameContainer;
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
    public boolean active;
    public ArrayList<ProjectileClass> project;
    
    public ProjectileClass(Vector2f vec, float w, float h)
    {
        Vector2f indVec = new Vector2f(vec);
        setVector(indVec);
        setWidthOffset(w);
        setHeightOffset(h);
        setupPolygon(vec, w, h);
        project = new ArrayList<ProjectileClass>();
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
    
    public void setActive(boolean b)
    {
        active = b;
    }
    
    public void addBullet_Enemy(EnemyClass enem)
    {
        for(int i = 0; i < 1; i++)
        {
            project.add(new ProjectileClass(enem.getVector(), 16, 16));
            setActive(true);
        }
    }
    
    public void addBullet_Player(PlayerClass player)
    {
        for(int i = 0; i < 1; i++)
        {
            //Vector2f vec = player.getVector();
            //vec.y = player.getVector().getY() + (player.getHeightOffset()/2);
            //project.add(new ProjectileClass(vec, 16, 16));
            project.add(new ProjectileClass(player.getVector(), this.getWidthOffset(), this.getHeightOffset()));
            setActive(true);
        }
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
    
    public ArrayList getProjectile()
    {
        return project;
    }
    
    public float getSpeed()
    {
        return speed;
    }
    
    public void update(GameContainer gc, int delta, PlayerClass player)
    {
        for(int i = 0; i < project.size(); i++)
        {
            if(project.get(i).getPolygon().getX() >= gc.getWidth() &&
                    project.get(i).getPolygon().getX() <= 0)
            {
                project.remove(i);
            }
        }
    }
    
    public void playerFired(boolean hasFired, PlayerClass player, int delta)
    {
        hasFired = player.activeFire;
        if(hasFired)
        {
            for(int i = 0; i < project.size(); i++)
            {
            switch(player.getDirection())
            {
                case 0:
                    project.get(i).getVector().x -= speed * delta;
                    project.get(i).getPolygon().setX(project.get(i).getVector().x);
                    break;
                case 1:
                    project.get(i).getVector().x += speed * delta;
                    project.get(i).getPolygon().setX(project.get(i).getVector().getX());
                    break;
                default:
                    break;
            }
            }
        }
    }
    
    public void playerSwing(PlayerClass player, int delta)
    {
        boolean hasFired = player.activeFire;
        double angle = 0;
        
        if(angle != 360)
        {
                angle++;
        }else{
                angle = 0;
        }
        if(hasFired)
        {
            
            for(int i = 0; i < project.size(); i++)
            {
                switch(player.getDirection())
                {
                    case 0:
                        project.get(i).getVector().x = (float)(player.getPolygon().getCenterX() + Math.cos(angle) * 1);
                        project.get(i).getVector().y = (float)(player.getPolygon().getCenterY() + Math.sin(angle) * 1);
                        project.get(i).getPolygon().setLocation(project.get(i).getVector().x, project.get(i).getVector().y);
                        break;
                    case 1:
                        project.get(i).getVector().x = (float)(player.getPolygon().getCenterX() - Math.cos(angle) * 1);
                        project.get(i).getVector().y = (float)(player.getPolygon().getCenterY() - Math.sin(angle) * 1);
                        project.get(i).getPolygon().setLocation(project.get(i).getVector().getX(), project.get(i).getVector().getY());
                    default:
                        break;
                }
            }
        }
    }

    public void enemyFired_bullet(boolean hasFired, EnemyClass enem, int delta)
    {
        hasFired = enem.activeFire;
        
        if(hasFired)
        {
            System.out.println("I Am running");
            for(int i = 0; i < project.size(); i++)
            {
                switch(enem.getDirection())
                {
                case 0:
                    project.get(i).getVector().x -= speed * delta;
                    project.get(i).getPolygon().setX(project.get(i).getVector().getX());
                    break;
                case 1:
                    project.get(i).getVector().x += speed * delta;
                    project.get(i).getPolygon().setX(project.get(i).getVector().getX());
                    break;
                default:
                    break;
                }
            }
        }
    }
    
    public void enemyFired_Swing(boolean hasFired, EnemyClass enem, int delta)
    {
        hasFired = enem.activeFire;
        double angle = 0;
        if(angle != 360)
        {
            angle++;
            if(angle == 360)
            {
                angle = 0;
            }
        }
        if(hasFired)
        {
            for(int i = 0; i < project.size(); i++)
            {
                switch(enem.getDirection())
                {
                    case 0:
                        project.get(i).getVector().x = (float)(enem.getPolygon().getCenterX() + Math.cos(angle) * 5);
                        project.get(i).getVector().y = (float)(enem.getPolygon().getCenterY() + Math.sin(angle) * 5);
                        break;
                    case 1:
                        project.get(i).getVector().x = (float)(enem.getPolygon().getCenterX() - Math.cos(angle) * 5);
                        project.get(i).getVector().y = (float)(enem.getPolygon().getCenterX() - Math.sin(angle) * 5);
                        break;
                    default:
                        break;
                }
            }
        }
        
    }
    
    public boolean getActiveStatus()
    {
        return active;
    }
}

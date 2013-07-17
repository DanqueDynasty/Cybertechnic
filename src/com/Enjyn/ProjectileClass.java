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
    public ArrayList<ProjectileClass> project;
    
    public ProjectileClass(Vector2f vec, float w, float h)
    {
        setVector(vec);
        setWidthOffset(w);
        setHeightOffset(h);
        setupPolygon(vec, w, h);
        project = new ArrayList<>();
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
    
    public void addBullet_Enemy(EnemyClass enem)
    {
        for(int i = 0; i < 1; i++)
        {
            Vector2f vec = enem.getVector();
            vec.y = enem.getVector().getY() - (enem.getHeightOffset()/2);
            project.add(new ProjectileClass(vec, 16, 16));
        }
    }
    
    public void addBullet_Player(PlayerClass player)
    {
        for(int i = 0; i < 1; i++)
        {
            Vector2f vec = player.getVector();
            vec.y = player.getVector().getY() - (player.getHeightOffset()/2);
            //project.add(new ProjectileClass(vec, 16, 16));
            project.add(this);
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
    
    public void update(int delta)
    {
    
    }
    
    public void playerFired(boolean hasFired, PlayerClass player, int delta)
    {
        hasFired = player.getFiredStatus();
        if(hasFired)
        {
            for(int i = 0; i < project.size(); i++)
            {
            switch(player.getDirection())
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

    public void enemyFired_bullet(boolean hasFired, EnemyClass enem, PlayerClass player, int delta)
    {
        hasFired = enem.getFiredStatus();
        if(hasFired)
        {
            for(int i = 0; i < project.size(); i++)
            {
                switch(enem.getDirection())
                {
                case 0:
                    project.get(i).getVector().x -= speed * delta;
                    project.get(i).getPolygon().setX(project.get(i).getVector().getX());
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
    
    public void enemyFired_Swing(boolean hasFired, EnemyClass enem, PlayerClass player, int delta)
    {
        hasFired = enem.getFiredStatus();
        
        
    }
}

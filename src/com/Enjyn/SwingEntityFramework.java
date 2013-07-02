/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Enjyn;

/**
 *
 * @author Nathan
 */

import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.geom.Polygon;

public interface SwingEntityFramework {
    //setup
    public void setVector(Vector2f vec);
    public void setWidthOffset(float w);
    public void setHeightOffset(float h);
    public void setupPolygon(Vector2f vec, float w, float h);
       
    //get
    public Vector2f getVector();
    public float getWidthOffset();
    public float getHeightOffset();
    public Polygon getPolygon();
}

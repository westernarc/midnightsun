/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

/**
 *
 * @author Adrian
 */
public class IndicatorBar extends Node {
    private float min;
    private float max;
    private float size;
    float width;
    float height;
    private float deltaValue;
    private float value;
    private Box barMesh;
    private Geometry bar;
    private Material barMat;

    public IndicatorBar(String name, float min, float max, float width, float height, AssetManager AM) {
        super(name);
        this.min = min;
        this.max = max;
        this.width = width;
        this.height = height;
        size = max - min;
        barMesh = new Box(width, height, 0.00001f);
        bar = new Geometry("bar", barMesh);
        barMat = new Material(AM, "Common/MatDefs/Misc/Unshaded.j3md");
        barMat.setTexture("m_ColorMap", AM.loadTexture("Textures/game/red.png"));
        bar.setMaterial(barMat);
        bar.move(width,0,0);
        move(-width*2,0,0);
        attachChild(bar);
    }
    public void setValue(float newValue) {
        value = newValue;
    }
    public void update(float indicatorValue) {
            deltaValue = value - indicatorValue;
            value = indicatorValue;
            //System.out.println("Change in bar value: " + value + " to " + indicatorValue + ": DV = " + deltaValue + ", SC = " + ((value-deltaValue)/value));
            if(value < max) {
                setLocalScale((value/max),1,1);
            } else {
                setLocalScale(1,1,1);
            }
    }

}

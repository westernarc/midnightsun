/*
 * Copyright 2012 Adrian Micayabas <deepspace30@gmail.com>
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
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

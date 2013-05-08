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
package STG.States;

import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 *
 * @author Adrian
 */
public class StateNode extends Node {
    protected boolean isComplete;
    protected float timer;
    protected Node rootNode;
    protected AssetManager assetManager;
    protected InputManager inputManager;
    protected Camera cam;
    
    public StateNode() {
        this("New State Node");
    }
    public StateNode(String name) {
        super(name);
        isComplete = false;
    }
    public StateNode(String name, Node rn, AssetManager am, InputManager im, Camera c) {
        this(name);
        setRootNode(rn);
        setAssetManager(am);
        setInputManager(im);
        setCamera(c);
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void complete() {
        isComplete=true;
    }
    
    public void init() {
        
    }
    
    public void update(float tpf) {
    }
    
    public float getTimer() {
        return timer;
    }
    
    public void setRootNode(Node rn) {
        rootNode = rn;
    }
    public void setAssetManager(AssetManager am) {
        assetManager = am;
    }
    public void setInputManager(InputManager im) {
        inputManager = im;
    }
    public void setCamera(Camera c) {
        cam = c;
    }
}

/*
 * Copyright (c) 2009-2010 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package STG;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;

public class TestOgreAnim extends SimpleApplication {

    boolean movement;
    private AnimChannel playerAnimChan;
    private AnimControl playerAnimCont;
    Player player;
    Spatial model;

    float modelAngle;
    public static void main(String[] args) {
        TestOgreAnim app = new TestOgreAnim();
        app.start();
    }

    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);
        cam.setLocation(new Vector3f(6.4013605f, 7.488437f, 12.843031f));
        cam.setRotation(new Quaternion(-0.060740203f, 0.93925786f, -0.2398315f, -0.2378785f));
        DirectionalLight dl = new DirectionalLight();
        DirectionalLight dl2 = new DirectionalLight();
        dl2.setDirection(new Vector3f(0f,1f,0f).normalizeLocal());
        dl2.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(-0f, -0f, -1).normalizeLocal());
        dl.setColor(ColorRGBA.White);
        rootNode.addLight(dl);
        //rootNode.addLight(dl2);

        inputManager.addListener(actList, new String[]{"left","right","up","down"});
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_G));
                player = new Player();
                model = (Spatial) assetManager.loadModel("Models/game/player.mesh.xml");
    }

    void spawnChar() {


        
        player.attachChild(model);
        playerAnimCont = model.getControl(AnimControl.class);
        playerAnimCont.addListener(animList);
        playerAnimChan = playerAnimCont.createChannel();
        playerAnimChan.setAnim("stand",2f);
        playerAnimChan.setLoopMode(LoopMode.Loop);
        playerAnimChan.setSpeed(0.2f);





        rootNode.attachChild(player);
    }
    boolean spawned = false;
    float life = 0;
    public void simpleUpdate(float tpf) {
        life += tpf;
        if(life > 0 && spawned == false) {
            spawnChar();
            spawned = true;
        }
        //Decide what animation state the model should have by looking at what variables are true.
        System.out.println(Boolean.toString(l) + Boolean.toString(r) + Boolean.toString(u) + Boolean.toString(d));
        if(u) {
            if(!playerAnimChan.getAnimationName().equals("up")&!l&!r&!d){
                playerAnimChan.setAnim("up",0.5f);
            }
        }
        if(d) {
            if(!playerAnimChan.getAnimationName().equals("down")&!u&!l&!r) {
                playerAnimChan.setAnim("down",0.5f);
            }
        }
        if(l) {
            if(!playerAnimChan.getAnimationName().equals("left")&!u&!r&!d){
                playerAnimChan.setAnim("left",0.5f);
            }
        }
        if(r) {
            if(!playerAnimChan.getAnimationName().equals("right")&!u&!l&!d){
                playerAnimChan.setAnim("right",0.5f);
            }
        }

        if(!l & !r & !u & !d) {
            if(!playerAnimChan.getAnimationName().equals("stand")) {
                playerAnimChan.setAnim("stand",0.2f);
            }
        }
    }

    AnimEventListener animList = new AnimEventListener() {

        public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {

        }

        public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {

        }
    };

    boolean l,r,u,d = false;
    ActionListener actList = new ActionListener() {

        public void onAction(String name, boolean isPressed, float tpf) {
            if (name.equals("left") && isPressed){
                l = true;
                if(r) {
                    r = false;
                }
            }
            if(name.equals("left") && !isPressed) {
                l = false;
            }

            if (name.equals("right") && isPressed) {
                r = true;
                if(l) {
                    l = false;
                }
            }
            if(name.equals("right") && !isPressed) {
                r = false;
            }

            if (name.equals("up") && isPressed) {
                u = true;
                if(d) {
                    d = false;
                }
            }
            if(name.equals("up") && !isPressed) {
                u = false;
            }

            if (name.equals("down") && isPressed) {
                d = true;
                if(u) {
                    u = false;
                }
            }
            if(name.equals("down") && !isPressed) {
                d = false;
            }
        }
    };
}

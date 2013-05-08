/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STG.States;

import STG.PanelNode;
import com.jme3.asset.AssetManager;
import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Node;

/**
 *
 * @author Adrian
 */
public class OpenSplashState extends StateNode {
    PanelNode openSplashPanel;
    
    public OpenSplashState() {
        super("openSplashState");
    }
    public OpenSplashState(String name, Node rn, AssetManager am, InputManager im, Camera c) {
        super(name,rn,am,im,c);
    }
    @Override
    public void init() {
        openSplashPanel = new PanelNode("openSplashPanel");
        openSplashPanel.setModel(assetManager.loadModel("Models/openSplash/logo.j3o"));
        openSplashPanel.setMat(new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md"));
        openSplashPanel.getMat().setTexture("m_ColorMap", assetManager.loadTexture("Models/openSplash/openSplashTex.png"));

        attachChild(openSplashPanel);
        DirectionalLight openSplashLight = new DirectionalLight();
        openSplashLight.setDirection(new Vector3f(0f, 0f, -1.0f));
        openSplashLight.setColor(ColorRGBA.White);

        addLight(openSplashLight);
        cam.lookAt(getLocalTranslation(),Vector3f.UNIT_Y);

        rootNode.attachChild(this);

        //Set up key binds
        inputManager.addMapping("advance", new MouseButtonTrigger(0));
        inputManager.addListener(openSplashListener, new String[]{"advance"});
    }
    @Override
    public void update(float tpf) {
        timer += 1/60f;
    }
    
    private ActionListener openSplashListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            //Open splash state must last 3 seconds.
            if (name.equals("advance") && timer > 2) {
                complete();
                inputManager.deleteMapping("advance");
            }
        }
    };
}
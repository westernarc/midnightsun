/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package STG.States;

import STG.PanelNode;
import com.jme3.material.Material;
import com.jme3.scene.Spatial;

/**
 *
 * @author Adrian
 */
public class MainMenuState extends StateNode {
    PanelNode titleBackground;
    Spatial titleBackgroundModel;
    Material titleBackgroundMat;
    
    PanelNode titlePanel;
    
    @Override
    public void init() {
        
    }
    @Override
    public void update(float tpf) {
        timer += 1/60f;
    }
}

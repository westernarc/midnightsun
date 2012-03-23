/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG.spell;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

/**
 *
 * @author Adrian
 */
public abstract class Spellcard {
    AssetManager assetManager;
    Node rootNode;

    public Spellcard(AssetManager aM, Node rN) {
        assetManager = aM;
        rootNode = rN;
    }
    public abstract void init();
    public abstract void update(float tpf);
    public abstract void end();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.scene.Node;

/**
 *
 * @author Adrian
 */
public class ButtonNode extends Node {
    private boolean isPressed;
    float defaultLoc;
    float indentLoc;
    
    public ButtonNode(String name) {
        super(name);
    }
    public void press() {
        isPressed=true;
    }
    public void release() {
        isPressed=false;
    }
    public boolean isPressed() {
        return isPressed;
    }
}

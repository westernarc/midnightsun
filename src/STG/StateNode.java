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
public class StateNode extends Node {
    private boolean isComplete;
    public StateNode(String name) {
        super(name);
        isComplete = false;
    }
    public boolean isComplete() {
        return isComplete;
    }
    public void complete() {
        isComplete=true;
    }
}

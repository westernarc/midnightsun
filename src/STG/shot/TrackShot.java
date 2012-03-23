/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG.shot;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 *
 * @author Adrian
 * This bullet is assigned a target vector at creation.
 * It will fly at a constant velocity until it hits the player or the
 * field bounds.
 */
class TrackShot implements Control {
    Vector3f target;
    Spatial spatial;
    
    public TrackShot(String name, Vector3f src, Vector3f target, Vector3f launch, float speed) {

    }
    public void update(float time) {
    }

    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void render(RenderManager rm, ViewPort vp) {
    }

    public void write(JmeExporter ex) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void read(JmeImporter im) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

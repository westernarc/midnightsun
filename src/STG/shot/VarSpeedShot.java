/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG.shot;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.renderer.ViewPort;
import java.io.IOException;

public class VarSpeedShot extends StraightShot {
    float lifetime = 0;
    float changeTime = 2;
    float changeSpeed = 2;
    boolean changed = false;
    //False = left, true = right

    public VarSpeedShot(Spatial spatial, Vector3f src, Vector3f dir, float speed, float t1, float s1) {
        super(spatial,src,dir,speed);
        changeTime = t1;
        changeSpeed = s1;
    }

    public void setTarget(Vector3f targ) {
        direction.set(targ.subtract(spatial.getLocalTranslation()));
    }
    public void setDirection(Vector3f dir) {
        direction.set(dir);
    }

    public void setPause(boolean pause) {
        paused = pause;
    }

    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }

    public void setEnabled(boolean enabled) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void update(float tpf) {
        if(!paused) {
            lifetime += tpf;
            if(lifetime > changeTime && !changed) {
                changed = true;
                speed = changeSpeed;
                velocity = direction.mult(speed);
            }
            spatial.move(velocity.mult(tpf));
        }
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

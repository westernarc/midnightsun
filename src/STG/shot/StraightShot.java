/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG.shot;

import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.io.IOException;

/**
 *
 * @author Adrian
 * Bullect control
 * It will fly at a constant velocity until it hits the player or the
 * field bounds.
 */
public class StraightShot implements Control {
    protected Spatial spatial;
    protected Vector3f direction;
    protected Vector3f velocity;
    protected float speed;
    protected float angle;
    protected boolean paused = false;
    
    public StraightShot(Spatial spatial, Vector3f src, Vector3f dir, float speed) {
        this.spatial = spatial;
        this.spatial.setLocalTranslation(src);
        direction = new Vector3f(dir);
        direction.set(direction.normalize());
        if(direction.x > 0) {
            angle = -FastMath.acos(direction.y);
        } else {
            angle = FastMath.acos(direction.y);
        }
        spatial.rotate(0,0,angle);
        this.speed = speed;
        velocity = new Vector3f(direction.mult(speed));
    }
    
    public void setTarget(Vector3f targ) {
        direction.set(targ.subtract(spatial.getLocalTranslation()).normalize());
        if(direction.x > 0) {
            angle = -FastMath.acos(direction.y);
        } else {
            angle = FastMath.acos(direction.y);
        }
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

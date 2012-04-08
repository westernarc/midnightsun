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
public class CurveShot1 implements Control {
    Spatial spatial;
    Vector3f direction1;
    Vector3f velocity;
    float speed;
    float angle;
    float turnRate;
    float t1; //Time until curve starts
    float t2; //Time until curve ends
    private float lifetime; //total time lived
    boolean paused = false;
    boolean right;
    
    public CurveShot1(Spatial spatial, Vector3f src, Vector3f dir1, boolean right, float turnRate, float t1, float t2,float speed) {
        this.spatial = spatial;
        this.spatial.setLocalTranslation(src);
        direction1 = new Vector3f(dir1);
        direction1.set(direction1.normalize());
        this.right = right;
        this.t1 = t1;
        this.t2 = t2;
        if(dir1.x > 0) {
            angle = FastMath.atan(direction1.y/direction1.x);
        } else {
            angle = FastMath.PI+FastMath.atan(direction1.y/direction1.x);
        }
        spatial.rotate(0,0,angle);
        this.speed = speed;
        this.turnRate = turnRate;
        velocity = new Vector3f(direction1.mult(speed));
    }
    
    public void setTarget(Vector3f targ) {
        direction1.set(targ.subtract(spatial.getLocalTranslation()));
    }
    public void setDirection(Vector3f dir) {
        direction1.set(dir);
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
            if(lifetime < t2 && lifetime > t1) {
                if(right) {
                    angle += turnRate*tpf;
                } else {
                    angle -= turnRate*tpf;
                }
            }
            direction1.x = FastMath.cos(angle);
            direction1.y = FastMath.sin(angle);
            velocity = direction1.mult(speed).clone();
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

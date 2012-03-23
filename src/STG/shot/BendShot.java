/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG.shot;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.jme3.renderer.ViewPort;
import java.io.IOException;

public class BendShot extends StraightShot {
    Vector3f targ;
    float lifetime;
    float granularity = 0.1f;
    float intensity = 0.1f;
    
    float dirAngle;
    float tarAngle;
    
    public BendShot(Spatial spatial, Vector3f src, Vector3f dir, Vector3f targ, float speed) {
        super(spatial,src,dir,speed);
        this.targ = targ.normalize();
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
        paused = !enabled;
    }

    public boolean isEnabled() {
        return !paused;
    }

    public void update(float tpf) {
        if(!paused) {
            lifetime += tpf;
            if(lifetime > granularity && direction.subtract(targ).length() > 0.05f) {
                if(direction.x >= 0 && targ.x >= 0) {
                    angle = FastMath.acos(direction.y);
                    tarAngle = -FastMath.acos(targ.y);
                } else if(direction.x < 0 && targ.x >= 0) {
                    angle = FastMath.acos(direction.y);
                    tarAngle = FastMath.acos(targ.y);
                } else if(direction.x >= 0 && targ.x < 0) {
                    angle = -FastMath.acos(direction.y);
                    tarAngle = -FastMath.acos(targ.y);
                } else {
                    angle = FastMath.acos(direction.y);
                    tarAngle = -FastMath.acos(targ.y);
                }
                if(FastMath.abs(angle - tarAngle) > 0.05f) {
                    if(tarAngle > dirAngle) {
                        angle += intensity;
                    } else {
                        angle -= intensity;
                    }
                }
                direction.set(FastMath.cos(angle + FastMath.HALF_PI),FastMath.sin(angle + FastMath.HALF_PI),0);
                lifetime = 0;
                velocity = direction.mult(speed);
            }
            spatial.setLocalRotation(new Quaternion());
            spatial.rotate(0,0,angle);
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

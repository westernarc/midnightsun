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

public class UncannySealShot extends StraightShot {
    float lifetime = 0;
    float bendTime1 = 2;
    float bendTime2 = 4;
    int bends = 0;
    float bendAngle1 = 0.2f;
    float bendAngle2 = 0.2f;
    boolean rightward = false;
    //False = left, true = right
    
    public UncannySealShot(Spatial spatial, Vector3f src, Vector3f dir, float speed, float t1, float t2, float d1, float d2, boolean right) {
        super(spatial,src,dir,speed);
        bendTime1 = t1;
        bendTime2 = t2;
        bendAngle1 = d1;
        bendAngle2 = d2;
        rightward = right;
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
            if(lifetime > bendTime1 && bends < 1) {
                bend();
                bends++;
            }
            if(lifetime > bendTime2 && bends < 2) {
                bend();
                bends++;
            }
            spatial.move(velocity.mult(tpf));
        }
    }

    private void bend() {
        if(rightward) {
            if(bends < 1) {
                angle += bendAngle1;
            } else {
                angle += bendAngle2;
            }
        } else {
            if(bends < 1) {
                angle -= bendAngle1;
            } else {
                angle -= bendAngle2;
            }
        }
        float x = FastMath.cos(angle);
        float y = FastMath.sin(angle);
        if(rightward) {
            direction.set(direction.add(x,y,0).normalize());
        } else {
            direction.set(direction.subtract(x,y,0).normalize());
        }
        velocity = direction.mult(speed);
        spatial.rotate(0,0,-angle);
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

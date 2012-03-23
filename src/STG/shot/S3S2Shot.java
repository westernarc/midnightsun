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
public class S3S2Shot extends StraightShot {
    //Knives that rotate in a circle with a given speed
    //then stop after a given time, rotate in place
    //and accelerate towards a new target.
    float radius;//Distance from origin
    float offset;
    float lifetime;//Time bullet has lived
    float t1;//Time until bullet stops and turns
    float t2;//Time spent turning
    Vector3f targ2;//2nd target
    float s2;//2nd speed
    float curSpeed = 0;//Speed after stopping to track the player
    //Speeds up until curSpeed = s2
    boolean targ2set = false;
    boolean ccw; //Counterclockwise vs clockwise
    
    public S3S2Shot(Spatial spatial, float offset, float speed, float radius, float t1,float t2, Vector3f targ2, float s2, boolean ccw) {
        super(spatial, Vector3f.ZERO, Vector3f.ZERO, speed);
        this.offset = offset;
        this.radius = radius;
        this.t1 = t1;
        this.t2 = t2;
        this.targ2 = targ2;
        this.s2 = s2;
        this.ccw = ccw;
        spatial.scale(0.1f);
    }
    
    public void setTarget(Vector3f targ) {
        super.setTarget(targ);
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
            if(spatial.getLocalScale().length() < 3) {
                spatial.scale(1+10f*tpf);
            }
            
            if(lifetime < t2 && lifetime < t1) {
                angle = (lifetime*speed + offset);
                if(!ccw) {
                    angle = -angle;
                }
                float x = FastMath.cos(angle)*radius;
                float y = FastMath.sin(angle)*radius;
                spatial.setLocalTranslation(x,y,0);
                spatial.setLocalRotation(new Quaternion());
                if(ccw) {
                    spatial.rotate(0,0,angle);
                } else {
                    spatial.rotate(0,0,angle + FastMath.PI);
                }
            } else if(lifetime < t2) {
                float difAngle = 0;
                float y = targ2.subtract(spatial.getLocalTranslation()).normalize().y;
                if(targ2.subtract(spatial.getLocalTranslation()).normalize().x > 0) {
                    difAngle = FastMath.asin(y);
                } else {
                    difAngle = -FastMath.asin(y)+FastMath.PI;
                }
                difAngle %= FastMath.TWO_PI;
                angle %= FastMath.TWO_PI;
                if(difAngle > angle) {
                    angle += 15*tpf;
                } else {
                    angle -= 15*tpf;
                }
                spatial.setLocalRotation(new Quaternion());
                if(ccw) {
                    spatial.rotate(0,0,angle+FastMath.HALF_PI*3);
                } else {
                    spatial.rotate(0,0,angle+FastMath.HALF_PI*3);
                }
                //setTarget(new Vector3f(targ2));
            } else {
                //Timer exceeds t2
                //Don't track continuously; create a new copy of
                //target vector after time2.
                if(!targ2set) {
                    setTarget(new Vector3f(targ2));
                    targ2set = true;
                }
                if(curSpeed < s2) {
                    curSpeed += tpf*4;
                }
                velocity = direction.mult(curSpeed);
                spatial.move(velocity.mult(tpf));
                spatial.setLocalRotation(new Quaternion());
                spatial.rotate(0,0,angle);
            }
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

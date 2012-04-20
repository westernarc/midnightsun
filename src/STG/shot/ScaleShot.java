/*
 * Copyright 2012 Adrian Micayabas <deepspace30@gmail.com>
 * This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package STG.shot;

import STG.StaticBullet;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
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
public class ScaleShot extends Bullet implements Control {
    Spatial spatial;
    Vector3f direction1;
    Vector3f velocity;
    float speed;
    float angle;
    float t1; //Time until scale starts
    float t2; //Death time
    private float lifetime; //total time lived
    boolean paused = false;
    
    public ScaleShot(Spatial spatial, Vector3f src, Vector3f dir1, float t1, float t2, float speed) {
        this.spatial = spatial;
        this.spatial.setLocalTranslation(src);
        direction1 = new Vector3f(dir1);
        direction1.set(direction1.normalize());
        this.t1 = t1;
        this.t2 = t2;
        if(dir1.x > 0) {
            angle = FastMath.atan(direction1.y/direction1.x);
        } else {
            angle = FastMath.PI+FastMath.atan(direction1.y/direction1.x);
        }
        spatial.rotate(0,0,angle);
        this.speed = speed;
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
            if(lifetime > t1) {
                spatial.scale(1-tpf);
            }
            if(lifetime > t2) {
                if(spatial instanceof StaticBullet) {
                    StaticBullet sb = (StaticBullet)spatial;
                    sb.detachAllChildren();
                    sb.removeFromParent();
                }
            }
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

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
package STG;

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
 * Reflect shot
 * Used for Reimu's Border reflect cards
 * Rectangular field that will reflect bullets.
 * FIELD MUST BE CENTERED TO 0,0,0
 */
class ReflectShotControl implements Control {
    Spatial spatial;
    Vector3f direction;
    Vector3f velocity;
    float speed;
    float angle;

    boolean polarity; //Determines border behavior.
    float xBound;
    float yBound;
    float distChange; //CHANGES BULLET DISTANCE, ASSUME BULLET ORIGIN IS 0,0,0
    boolean reflected1 = false; //Bullets should only be reflected twice, at certain points.
    boolean reflected2 = false;
    
    public ReflectShotControl(Spatial spatial, Vector3f src, Vector3f dir, float speed, float xBound, float yBound, float distChange, boolean polarity) {
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
        this.xBound = xBound;
        this.yBound = yBound;
        this.distChange = distChange;
        this.polarity = polarity;
    }
    
    public void setTarget(Vector3f targ) {
        direction.set(targ.subtract(spatial.getLocalTranslation()));
    }
    public void setDirection(Vector3f dir) {
        direction.set(dir);
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
        spatial.move(velocity.mult(tpf));
        if(!reflected1) {
            if(spatial.getLocalTranslation().x > xBound || spatial.getLocalTranslation().x < -xBound || spatial.getLocalTranslation().y > yBound || spatial.getLocalTranslation().y < -yBound) {
                velocity.set(velocity.mult(-1));
                reflected1 = true;
                spatial.setLocalTranslation(spatial.getLocalTranslation().mult(distChange));
            }            
        } else {
            if(!reflected2) {
                if((spatial.getLocalTranslation().y < yBound+0.5 && spatial.getLocalTranslation().y > -yBound-0.5) && (spatial.getLocalTranslation().x < xBound+0.5 && spatial.getLocalTranslation().x > -xBound-0.5) ) {
                    velocity.set(velocity.mult(-1));
                    reflected2 = true;
                    spatial.setLocalTranslation(spatial.getLocalTranslation().mult(distChange+0.05f));
                }
            }
        }
        velocity.set(velocity.mult(0.9999f));
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

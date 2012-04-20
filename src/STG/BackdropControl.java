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
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.io.IOException;
/*
 * Node represents scrolling background
 *  stores length of background
 *  speed of scrolling
 *  Stage logic must be handled in main class
 *  ie. model switching on stage change
 */
public class BackdropControl implements Control {
    Spatial spatial;
    final static int STAGE_ONE = 1;
    private float offset;
    private int stage;
    private float length;
    private float speed;
    private float cycleTime;
    private float cycleLength;
    private float distanceMoved;
    private boolean enabled;
    float timescale;
    public void setTimescale(float ts) {
        timescale = ts;
    }
    public float getTimescale() {
        return timescale;
    }
    public BackdropControl(Spatial spatial, int stage, float length, float speed, float timeOffset, float cycleLength) {
        this.spatial = spatial;
        this.stage = stage;
        this.length = length;
        this.speed = speed;
        distanceMoved = 0;
        cycleTime = timeOffset;
        this.cycleLength = cycleLength;
        enabled = true;
    }

    public void update(float tpf) {
        tpf *= timescale;
        if(enabled) {
            spatial.move(0 , -tpf * speed, 0);
            cycleTime -= tpf;
            if(cycleTime < 0) {
                cycleTime = cycleLength;
                spatial.move(0f , length*3, 0f);
                distanceMoved = 0;
            }
        }
    }

    public void changeStage(int stage) {
        this.stage = stage;
    }

    public Control cloneForSpatial(Spatial spatial) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void setSpatial(Spatial spatial) {
        this.spatial = spatial;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public boolean isEnabled() {
        return enabled;
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

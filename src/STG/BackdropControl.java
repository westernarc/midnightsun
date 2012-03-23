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

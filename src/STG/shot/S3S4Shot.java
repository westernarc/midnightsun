package STG.shot;

import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResults;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.math.FastMath;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import java.io.IOException;

public class S3S4Shot extends StraightShot {
    //Knives that bounce off walls once
    boolean bounced = false;
    float horzBound;
    float vertUpperBound;
    float vertLowerBound;
    
    public S3S4Shot(Spatial spatial, Vector3f src, Vector3f dir, float speed, float horz, float vertU, float vertL) {
        super(spatial, src, dir, speed);
        horzBound = horz;
        vertUpperBound = vertU;
        vertLowerBound = vertL;
    }
    
    public void update(float tpf) {
        if(!paused) {
            if(!bounced){
                if(spatial.getLocalTranslation().x > horzBound) {
                    direction.x *= -1;
                    bounced = true;
                    angle = -angle;
                    spatial.setLocalRotation(new Quaternion());
                    spatial.rotate(0,0,angle);
                }
                if(spatial.getLocalTranslation().x < -horzBound) {
                    direction.x *= -1;
                    bounced = true;
                    angle = -angle;
                    spatial.setLocalRotation(new Quaternion());
                    spatial.rotate(0,0,angle);
                }
                if(spatial.getLocalTranslation().y > vertUpperBound) {
                    direction.y *= -1;
                    bounced = true;
                    angle = -angle + FastMath.PI;
                    spatial.setLocalRotation(new Quaternion());
                    spatial.rotate(0,0,angle);
                }
                if(spatial.getLocalTranslation().y < vertLowerBound) {
                    direction.y *= -1;
                    bounced = true;
                    angle = -angle + FastMath.PI;
                    spatial.setLocalRotation(new Quaternion());
                    spatial.rotate(0,0,angle);
                }
            }
            velocity = direction.mult(speed);
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

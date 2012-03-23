package STG.test;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture;
import java.util.logging.Level;

/** Sample 11 - how to create fire, water, and explosion effects. */
public class MovieTest extends SimpleApplication {
    Spatial movie;
    Material movieMat;
    Texture[] frame;
    int frames = 26;
    float frameRate = 0.15f;
    float time;

    int curFrame = 1;

    public static void main(String[] args) {
        MovieTest app = new MovieTest();
        AppSettings settings = new AppSettings(true);
        settings.put("Width", 1280);
        settings.put("Height", 720);
        settings.put("Title", "Title Screen");
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {

        movie = assetManager.loadModel("Models/mainMenu/movie.mesh.xml");
        movie.move(0,0,-13);
        movieMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        movie.setMaterial(movieMat);
        frame = new Texture[frames+1];
        for(int i = 1; i <= frames; i++) {
            TextureKey key = new TextureKey("Textures/mainMenu/frame" + i + ".png", false);
            frame[i] = assetManager.loadTexture(key);
            System.out.println(i);
        }
        movieMat.setTexture("m_ColorMap", frame[1]);
        rootNode.attachChild(movie);
    }

    @Override
    public void simpleUpdate(float tpf) {
        time += tpf;
        if(time > frameRate) {
            movieMat.setTexture("m_ColorMap", frame[curFrame]);
            curFrame++;
            if(curFrame > 26) {
                curFrame = 1;
            }
            time = 0;
        }
    }


}
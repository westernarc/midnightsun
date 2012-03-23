package STG;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.TextureKey;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.texture.Texture;
import com.jme3.util.SkyFactory;
import com.jme3.util.TangentBinormalGenerator;


public class LightBlow_Fog_System extends SimpleApplication {




  public static void main(String[] args) {
    LightBlow_Fog_System app = new LightBlow_Fog_System();
    app.start();
  }


 Node fog = new Node();

  @Override
  public void simpleInitApp() {

      TextureKey skyhi = new TextureKey("Textures/Water256.dds", true);
        skyhi.setGenerateMips(true);
        skyhi.setAsCube(true);
      final  Texture texhi = assetManager.loadTexture(skyhi);

      final  Texture texlow = assetManager.loadTexture(skyhi);
         rootNode.attachChild(SkyFactory.createSky(assetManager, texlow, false));



    Spatial char_boy = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat = assetManager.loadMaterial("Materials/LightBlow/Fog_System/LightBlow_Fog.j3m");
    char_boy.setMaterial(mat);
    TangentBinormalGenerator.generate(char_boy);

        Node fog1[] = new Node [20];
        for (int i=0; i<fog1.length; i++) {

        Node ndd = new Node("fog1_"+i);
        ndd.attachChild(char_boy.clone());
        ndd.setLocalTranslation(0, 0, -(i+3)*5);
        System.out.println(ndd.getName());
        fog.attachChild(ndd);
        }

    Spatial char_boy2 = assetManager.loadModel("Models/LightBlow/jme_lightblow.blend");
    Material mat2 = assetManager.loadMaterial("Materials/LightBlow/Fog_System/LightBlow_Fog_Skybox.j3m");
    char_boy2.setMaterial(mat2);
    char_boy2.setLocalTranslation(2.0f, 0, 0);
    TangentBinormalGenerator.generate(char_boy2);

        Node fog2[] = new Node [20];
        for (int i=0; i<fog2.length; i++) {

        Node ndd = new Node("fog2_"+i);
        ndd.attachChild(char_boy2.clone());
        ndd.setLocalTranslation(3, 0, -(i+3)*5);
        System.out.println(ndd.getName());
        fog.attachChild(ndd);
        }

        rootNode.attachChild(fog);

        flyCam.setMoveSpeed(50);


        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.8f, -0.6f, -0.08f).normalizeLocal());
        dl.setColor(new ColorRGBA(1,1,1,1));
        rootNode.addLight(dl);
  }


@Override
    public void simpleUpdate(float tpf){
    }
}



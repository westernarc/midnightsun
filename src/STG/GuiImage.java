/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;

/**
 *
 * @author Adrian
 */
public class GuiImage extends Geometry {
    private int width, height;

    public GuiImage(String name, Mesh mesh) {
        super(name, mesh);
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }
    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public float getX() {
        return getLocalTranslation().x;
    }
    public float getY() {
        return getLocalTranslation().y;
    }
}

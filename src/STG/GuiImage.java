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

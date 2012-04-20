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

import com.jme3.bounding.BoundingSphere;
import com.jme3.bounding.BoundingVolume;
import com.jme3.math.Vector3f;

/**
 *Represents the bullet model.
 * @author Adrian
 */
public class StaticBullet extends GameObject {
    BoundingVolume bulletBound;
    private float hitSize = 1f;

    boolean live = true;
    
    public float getHitSize() {
        return hitSize;
    }
    public void kill() {
        hitSize = 0;
        live = false;
    }
    public boolean isLive() {
        return live;
    }
    public StaticBullet(String name, float size) {
        super(name);
        bulletBound = new BoundingSphere(size, getLocalTranslation());
    }
    
    public BoundingVolume getBulletBound() {
        return bulletBound;
    }
}

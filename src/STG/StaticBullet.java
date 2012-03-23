/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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

    public float getHitSize() {
        return hitSize;
    }
    public StaticBullet(String name, float size) {
        super(name);
        bulletBound = new BoundingSphere(size, getLocalTranslation());
    }

    public BoundingVolume getBulletBound() {
        return bulletBound;
    }
}

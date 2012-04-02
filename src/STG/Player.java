/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingSphere;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.Vector3f;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
/**
 *Player subclass.  Helps keep track of variables that will make it easier
 * to calculate radial coordinates.
 * @author Adrian
 */
public class Player extends GameObject {
    public float moveSpeed = 10f;
    public float turnSpeed = 1.2f;
    Vector3f direction;
    Vector3f velocity;
    float stageAngle;
    float distance = 0;
    private int life;
    public static final int MAX_LIFE = 50;
    private float hitSize = 0.1f;
    private float grazeSize = 10;
    
    public float getHitSize() {
        return hitSize;
    }
    public float getGrazeSize() {
        return grazeSize;
    }
    public void setLife(int life) {
        this.life = life;
    }
    public void changeLife(int change) {
        this.life += change;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public float getDistance() {
        return distance;
    }

    public int getLife() {
        return life;
    }

    public float getMoveSpeed() {
        return moveSpeed;
    }

    public float getStageAngle() {
        return stageAngle;
    }

    public float getTurnSpeed() {
        return turnSpeed;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public Player() {
        super("player");
        life = 100;
    }

    public float getX() {
        return this.getLocalTranslation().x;
    }
    public float getY() {
        return this.getLocalTranslation().y;
    }
    public float getZ() {
        return this.getLocalTranslation().z;
    }


}

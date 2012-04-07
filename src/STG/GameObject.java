/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;

/**
 *
 * @author Adrian
 */
public class GameObject extends Node {
    Vector3f direction;
    Vector3f velocity;
    Vector3f destination;
    float speed;
    float angle;
    float scale = 1;
    float targScale;
    float scaleSpeed;

    public Vector3f getDestination() {
        return destination;
    }
    public GameObject(String name) {
        super(name);
        direction = new Vector3f(0,0,1);
        velocity = new Vector3f(0,0,0);
        destination = new Vector3f(0,0,0);
        speed = 0.001f;
    }

    public void rotate(float angle) {
        super.rotate(0,0,angle);
        this.angle -= angle;
        if(angle / com.jme3.math.FastMath.PI > 1) {
            angle -= com.jme3.math.FastMath.PI;
        } else if(angle / com.jme3.math.FastMath.PI < -1) {
            angle += com.jme3.math.FastMath.PI;
        }
        direction.x = com.jme3.math.FastMath.sin(this.angle);
        direction.y = com.jme3.math.FastMath.cos(this.angle);
    }

    public void moveTo(Vector3f dest, float speed) {
        destination.set(dest);
        this.speed = speed;
    }

    public void moveTo(float x, float y, float z, float speed) {
        moveTo(new Vector3f(x,y,z),speed);
    }
    public void scaleTo(float targScale, float speed) {
        this.targScale = targScale;
        scaleSpeed = speed;
    }

    public void clearDest() {
        destination.set(this.getLocalTranslation());
    }
    public void update(float time) {
        direction = destination.subtract(this.getLocalTranslation());
        velocity = direction.mult(speed);
        if(this.getLocalTranslation().distance(destination) < 1) {
            clearDest();
        }
        this.move(velocity.mult(time));
        float scaleDiff = targScale - scale;
        if(scaleDiff > 0.1) {
            scale += scaleDiff*scaleSpeed;
        } else if (scaleDiff < 0.1) {
            scale -= scaleDiff*scaleSpeed;
        }
    }

    public float getX() {
        return getLocalTranslation().x;
    }
    public float getY() {
        return getLocalTranslation().y;
    }
    public float getZ() {
        return getLocalTranslation().z;
    }

    public void setX(float newX) {
        setLocalTranslation(newX, getY(), getZ());
    }

    public void setY(float newY) {
        setLocalTranslation(getX(), newY, getZ());
    }

    public void setZ(float newZ) {
        setLocalTranslation(getX(), getY(), newZ);
    }

    public Vector3f getPos() {
        return getLocalTranslation();
    }

    public void setPos(float x, float y, float z) {
        setLocalTranslation(x,y,z);
    }

    public void setPos(Vector3f pos) {
        setLocalTranslation(pos);
    }
}

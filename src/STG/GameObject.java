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

    boolean movingInto = false;
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

    //Moves through target
    public void moveInto(Vector3f dest, float speed) {
        destination.set(dest);
        movingInto = true;
        direction = dest.subtract(getPos());
        this.speed = speed;
    }
    
    //Moves to target and slows down
    public void moveTo(Vector3f dest, float speed) {
        destination = dest;
        this.speed = speed;
    }

    public void moveTo(float x, float y, float z, float speed) {
        moveTo(new Vector3f(x,y,z),speed);
    }
    public void scaleTo(float targScale, float speed) {
        this.targScale = targScale;
        scaleSpeed = speed;
    }
    public Vector3f getDirection() {
        return direction;
    }
    public void clearDest() {
        destination.set(this.getLocalTranslation());
    }
    public void update(float time) {
        if(!movingInto) {
            direction = destination.subtract(this.getLocalTranslation());
        }
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
        this.scale(1/scale);
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

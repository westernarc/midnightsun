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
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
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
    public static final int MAX_LIFE = 150;
    private float hitSize = 0.1f;
    private float grazeSize = 12;
    
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

    public void lookAt(Vector3f position, Vector3f upVector) {
        setLocalRotation(new Quaternion());
        float difX = position.x - getX();
        float difY = position.y - getY();
        float difZ = position.z;
        float angleX = FastMath.atan(difX*10/difZ);
        float angleY = -FastMath.atan(difY*10/difZ);
        float turnLimit = FastMath.QUARTER_PI*4/5;
        if(angleX > turnLimit) {
            angleX = turnLimit;
        }
        if(angleX < -turnLimit) {
            angleX = -turnLimit;
        }
        if(angleY > turnLimit) {
            angleY = turnLimit;
        }
        if(angleY < -turnLimit) {
            angleY = -turnLimit;
        }
        this.rotate(angleY,angleX,0);
    }
}

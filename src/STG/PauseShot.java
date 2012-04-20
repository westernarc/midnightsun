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

import com.jme3.math.Vector3f;

/**
 *
 * This bullet is assigned a launch vector and a target vector.
 * It will follow the launc vector for a certain time, then it will
 * move towards the target vector.
 */
class PauseShot extends GameObject {
    Vector3f target;
    Vector3f correction;
    float life;
    boolean correctionMade;
    public PauseShot(String name, Vector3f src, Vector3f target, Vector3f launch, float speed) {
        super(name);
        this.setLocalTranslation(src);
        direction.set(launch.subtract(this.getLocalTranslation()));
        direction.normalize();
        velocity = direction.mult(speed);
        this.target= new Vector3f(target);
    }
    public void update(float time) {
        life += time;
        if(life<1) {
        this.move(velocity);
        }
        if(life > 1 && !correctionMade) {
            correction = target.subtract(this.getLocalTranslation());
            correctionMade = true;
        }
        if(life > 2) {
            this.move(correction.mult(speed));
        }
    }
}

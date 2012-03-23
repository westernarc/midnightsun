/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

import com.jme3.math.Vector3f;

/**
 *
 * @author Adrian
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

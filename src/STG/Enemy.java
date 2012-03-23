/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package STG;

/**
 *
 * @author Adrian
 */
public class Enemy extends GameObject {
    float life = 100;
    private float hitSize = 5;
    
    public float getHitSize() {
        return hitSize;
    }
    public Enemy() {
        super("enemy");
    }
    public void update(float time) {
        //Make enemies move smoothly.
        super.update(time);
    }
}

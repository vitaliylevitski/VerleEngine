package VerleEng;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;

import java.util.ArrayList;

public class DistanceJoint extends Joint {
    public float length;
    public boolean isCollisible=false;
    public CollisionLine collisionLine;
    public DistanceJoint(Particle p1, Particle p2, float k)
    {
        this.p1 = p1;
        this.p2 = p2;
        length = Vector2.lenth(p1.pos, p2.pos);
        this.k = k;
    }
    public void connect()
    {
        Vector2 dpos = Vector2.sub(p1.pos, p2.pos);
        float l=dpos.lenth();
        float dl=length-l;
        float diff = dl / l;
        p1.pos = Vector2.sum(p1.pos, Vector2.mult(dpos, k * diff * p2.mass / (p1.mass + p2.mass)));
        p2.pos = Vector2.sub(p2.pos, Vector2.mult(dpos, k * diff * p1.mass / (p1.mass + p2.mass)));
    }
}

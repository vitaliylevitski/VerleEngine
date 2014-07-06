package VerleEng;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;
public class PositionJoint extends VerleEng.Joint {
    public PositionJoint(VerleEng.Particle p1, VerleEng.Particle p2, float k)
    {
        this.p1 = p1;
        this.p2 = p2;
        this.k = k;
    }
    public  void connect()
    {
        Vector2 pos = Vector2.div(Vector2.sum(Vector2.mult(p1.pos, p1.mass), Vector2.mult(p2.pos, p2.mass)), (p1.mass + p2.mass));
        p1.pos = Vector2.sum(p1.pos, Vector2.mult(Vector2.sub(pos, p1.pos), k));
        p2.pos = Vector2.sum(p1.pos, Vector2.mult(Vector2.sub(pos, p2.pos), k));
    }
}

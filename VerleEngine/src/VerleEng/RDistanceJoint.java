package VerleEng;

import MathVector.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 07.06.14
 * Time: 0:44
 * To change this template use File | Settings | File Templates.
 */
public class RDistanceJoint extends  DistanceJoint {
    public float restitution;

    public RDistanceJoint(Particle p1, Particle p2, float k,float restitution) {
        super(p1,p2,k);
        this.restitution=restitution;
    }

    public void connect()
    {
        Vector2 dpos = Vector2.sub(p1.pos, p2.pos);
        float l=dpos.lenth();
        float dl=length-l;
        float diff = dl / l;
        Vector2 v1=Vector2.sub(p1.pos,p1.posLast),v2=Vector2.sub(p2.pos,p2.posLast),dv=Vector2.sub(v1,v2);
        Vector2 dvpro=Vector2.mult(Vector2.proectTo(dv,dpos),restitution/2);
        p1.pos = Vector2.sum(p1.pos, Vector2.mult(Vector2.sub(Vector2.mult(dpos,k * diff),dvpro),p2.mass / (p1.mass + p2.mass)));
        p2.pos = Vector2.sub(p2.pos, Vector2.mult(Vector2.sub(Vector2.mult(dpos,k * diff),dvpro),p1.mass / (p1.mass + p2.mass)));
    }

}

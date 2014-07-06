package VerleEng;

import MathVector.Vector2;

public class LineJoint extends  Joint{
    DistanceJoint j;
    Particle p3;
    float position;
    public LineJoint(Particle p,DistanceJoint j,float position,float k){
        this.j = j;
        p1 = j.p1;
        p2 = j.p2;
        p3 = p;
        this.position = position;
        this.k = k;
    }
    @Override
    public void connect(){
        Vector2 posJ=Vector2.sum(Vector2.mult(p1.pos,position),Vector2.mult(p2.pos,(1-position)));
        float k1=position,k2=1-position;
        Vector2 dl= Vector2.sub(p3.pos,posJ);
        p3.pos= Vector2.sub(p3.pos, Vector2.mult(dl, (p1.mass * k2 + p2.mass * k1) / (p1.mass * k2 + p2.mass * k1 + p3.mass)));
        p1.pos= Vector2.sum(p1.pos, Vector2.mult(dl, p3.mass / (p1.mass * k2 + p2.mass * k1 + p3.mass) * k2));
        p2.pos= Vector2.sum(p2.pos, Vector2.mult(dl, p3.mass / (p1.mass * k2 + p2.mass * k1 + p3.mass) * k1));
    }
}

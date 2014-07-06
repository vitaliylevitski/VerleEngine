package VerleEng;

import VerleEng.Collisions;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 19.04.14
 * Time: 3:12
 * To change this template use File | Settings | File Templates.
 */
public class CollisionLine{
    public int collisionGroup;
    public int antiCollisionGroup;
    Particle p1,p2;
    public float friction;
    ArrayList<Collision> collisions;
    DistanceJoint j;
    public CollisionLine(DistanceJoint j,int collisionGroup,int antiCollisionGroup){
        p1=j.p1;
        p2=j.p2;
        friction=j.k;
        this.collisionGroup=collisionGroup;
        this.antiCollisionGroup=antiCollisionGroup;
        collisions=new ArrayList<Collision>();
        this.j=j;
        j.isCollisible=true;
        j.collisionLine=this;
    }

    public ArrayList<Particle> getCollisionParticles(){
        ArrayList<Particle> particles=new ArrayList<Particle>();
        for(Collision c : collisions)
            if(c.active) particles.add(c.p);
        return  particles;
    }
}

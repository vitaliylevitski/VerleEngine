package VerleEng;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;
public class Collisions{
    public ArrayList<CollisionLine> lines;
    public ArrayList<Particle>parts;

    public Collisions(){
        lines=new ArrayList<VerleEng.CollisionLine>();
        parts=new ArrayList<Particle>();
    }

    public void collideResolution(){
        for (CollisionLine l1 : lines)
        for(int i=0;i<parts.size();i++)
            if (l1.collisionGroup == parts.get(i).collisionGroup && (l1.antiCollisionGroup != parts.get(i).antiCollisionGroup||l1.antiCollisionGroup==0)&&l1.p1!=parts.get(i)&&l1.p2!=parts.get(i))
            {
               checkCollision(parts.get(i), l1, i);
            }
    }

    private void checkCollision(Particle p, CollisionLine l,int index){
        Vector2 r =Vector2.sub(l.p1.pos,l.p2.pos);
        Vector2.Position pos = p.pos.rightOrLeft(l.p1.pos, l.p2.pos);
        l.collisions.get(index).positions.add(pos); ;
            if (l.collisions.get(index).active)
            {
                Vector2.Position collissionPos1 = p.posLast.rightOrLeft(l.p1.pos, Vector2.sum(l.p1.pos, new Vector2(r.y, -r.x)));
                Vector2.Position collisionPos2 = p.posLast.rightOrLeft(l.p2.pos, Vector2.sum(l.p2.pos, new Vector2(r.y, -r.x)));
                if (collissionPos1 !=collisionPos2)
                {
                    collide(l.collisions.get(index), l);
                }
                else
                    l.collisions.get(index).active = false;
            }
        if (pos != l.collisions.get(index).positions.get(l.collisions.get(index).positions.size() - 2))
        {
            l.collisions.get(index).active = !l.collisions.get(index).active;
        }
        l.collisions.get(index).positions.remove(l.collisions.get(index).positions.get(0));
    }

    private void collide(Collision c, VerleEng.CollisionLine l){
        VerleEng.Particle p=c.p;
        Vector2 dpos1= Vector2.sub(l.p1.pos, l.p2.pos),dpos2= Vector2.sub(p.pos, l.p1.pos);
        Vector2 v1=Vector2.sub(p.pos,p.posLast),v2=Vector2.sub(l.p1.pos,l.p1.posLast),v3=Vector2.sub(l.p2.pos, l.p2.posLast);
        float length=dpos1.lenth();
        Vector2 pro = Vector2.proectTo(dpos2, dpos1);
        float d= -(dpos1.y * dpos2.x - dpos1.x * dpos2.y) / length;
        float k1=pro.lenth()/length;
        float k2=1-k1;
        Vector2 dv=Vector2.sub(v1, Vector2.sum(Vector2.mult(v2, k1), Vector2.mult(v3, k2)));
        dv=Vector2.proectTo(dv,dpos1);
        Vector2 dl= Vector2.mult(Vector2.turned(dpos1), d / length);
        p.pos= Vector2.sum(p.pos, Vector2.mult(dl, (l.p1.mass * k2 + l.p2.mass * k1) / (l.p1.mass * k2 + l.p2.mass * k1 + p.mass)));
        l.p1.pos= Vector2.sub(l.p1.pos, Vector2.mult(dl, p.mass / (l.p1.mass * k2 + l.p2.mass * k1 + p.mass) * k2));
        l.p2.pos= Vector2.sub(l.p2.pos, Vector2.mult(dl, p.mass / (l.p1.mass * k2 + l.p2.mass * k1 + p.mass) * k1));
        p.pos= Vector2.sub(p.pos, Vector2.mult(dv, (l.p1.mass + l.p2.mass) / (l.p1.mass + l.p2.mass + p.mass) * l.friction * p.friction));
        l.p1.pos= Vector2.sum(l.p1.pos, Vector2.mult(dv, p.mass / (l.p1.mass + l.p2.mass + p.mass) * l.friction * p.friction));
        l.p2.pos= Vector2.sum(l.p2.pos, Vector2.mult(dv, p.mass / (l.p1.mass + l.p2.mass + p.mass) * l.friction * p.friction));

    }

    public void addLine(CollisionLine l)
    {
        lines.add(l);
        for (Particle p : parts)
        {
            Vector2.Position pos = p.pos.rightOrLeft(l.p1.pos, l.p2.pos);
            Collision col = new Collision(p);
            l.collisions.add(col);
            col.positions.add(pos);
        }
    }
    public void addParticle(Particle p)
    {
        for(CollisionLine l :lines)
        {
            Vector2.Position pos=p.pos.rightOrLeft(l.p1.pos, l.p2.pos);
            Collision c=new Collision(p);
            c.positions.add(pos);
            l.collisions.add(c);
        }
        parts.add(p);
    }
}





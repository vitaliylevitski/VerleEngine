package VerleEng;

import VerleEng.Joint;
import VerleEng.Particle;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;
public class VerleEngine
{
    public ArrayList<Particle> parts;
    public ArrayList<Joint> connections;
    public Vector2 gravity;
    public interface Func<TInput, TResult>
    {
        TResult call(TInput target);
    }
    public interface Action<T>
    {
        void call(T target) ;
    }
    Action<VerleEng.Particle> highR;
    Action<VerleEng.Particle> lowR;
    public VerleEng.Collisions collisions;
    public VerleEngine(Vector2 gravity)
    {
        parts = new ArrayList<VerleEng.Particle>();
        connections = new ArrayList<VerleEng.Joint>();
        this.gravity = gravity;
        highR=new Action<VerleEng.Particle>() {
            @Override
            public void call(VerleEng.Particle p)  {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        };
        lowR=highR;
        collisions=new Collisions();
    }

    public void step(float dt, int n)
    {
        for (int i = 0; i < parts.size(); i++)
            parts.get(i).a = Vector2.sum(gravity, parts.get(i).a);
        for (int i = 0; i < parts.size(); i++)
            parts.get(i).integrate(dt);
        for (int i = 0; i < parts.size(); i++)
            lowR.call(parts.get(i));
        connectionResolution(n);
        for (int i = 0; i < parts.size(); i++)
            highR.call(parts.get(i));
        collisions.collideResolution();
    }

    void connectionResolution(int n)
    {
        for (int k = 0; k < n; k++)
            for (int i = 0; i < parts.size(); i++)
                for (int j = 0; j < parts.get(i).connections.size(); j++)
                {
                    for (int f = 0; f < parts.get(i).connections.size(); f++)
                    {
                        parts.get(i).connections.get(f).connect();
                    }
                }
    }

    public void add(VerleEng.Particle p)
    {
        parts.add(p);
    }

    public void add(VerleEng.Joint c)
    {
        connections.add(c);
        c.p1.connections.add(c);
        c.p2.connections.add(c);
    }

    public void add(VerleEng.Particle... parts)
    {
        for (VerleEng.Particle p : parts)
        {
            this.parts.add(p);
        }
    }

    public void remove(Particle...parts)
    {
        if(parts==null)return;
        for (VerleEng.Particle p : parts)
        {
            for(Joint j: p.connections)
            {
                j.p1.connections.remove(j);
                j.p2.connections.remove(j);
                connections.remove(j);
            }
            this.parts.remove(p);
        }
    }

    public void add(VerleEng.DistanceJoint... connections)
    {
        for (VerleEng.DistanceJoint c : connections)
        {
            this.connections.add(c);
            c.p1.connections.add(c);
            c.p2.connections.add(c);
        }
    }

    public void add(VerleEng.Struct... structures)
    {
        for (VerleEng.Struct s : structures)
        {
            if(s.parts!=null)
                for (int i = 0; i < s.parts.length; i++)
                    add(s.parts[i]);
            if(s.connections!=null)
                for (int i = 0; i < s.connections.length; i++)
                    add(s.connections[i]);
            if(s.lines!=null)
                for (int i = 0; i < s.lines.length; i++)
                {
                    collisions.addLine(s.lines[i]);
                    collisions.addParticle(s.lines[i].p1);
                    collisions.addParticle(s.lines[i].p2);
                }
        }
    }


    public enum RestrictionPriority{High,Low};
    public void addRestruction(final Action<VerleEng.Particle> r, RestrictionPriority priority)
    {
        if (priority == RestrictionPriority.High){
              final Action<VerleEng.Particle> ac=highR;
              highR = new Action<VerleEng.Particle>() {
              @Override
              public void call(VerleEng.Particle p) {
                ac.call(p);
                r.call(p);
             }
           };
        }
        if (priority == RestrictionPriority.Low){
            final Action<VerleEng.Particle> ac=lowR;
            lowR=new Action<VerleEng.Particle>() {
                @Override
                public void call(VerleEng.Particle p) {
                    ac.call(p);
                    r.call(p);
                }
            };
        }
    }
    public void addHeightRestruction(final Func<Float, Float> f,final float k1,final float k2,final RestrictionPriority priority, final boolean over)
    {
        final Action<VerleEng.Particle> r=new Action<VerleEng.Particle>() {
            @Override
            public void call(VerleEng.Particle p) {
                float l = f.call(p.pos.x);
                float dl = p.pos.y - l;
                if (!over) dl *= -1;
                if (dl < 0)
                {
                    if (!over) dl *= -1;
                    float b = (f.call(p.pos.x+0.001f)-f.call(p.pos.x))/0.001f;
                    if (Math.abs(b) > 1)
                        dl /= Math.abs(b);
                    Vector2 v = Vector2.sub(p.pos, p.posLast);
                    p.pos = Vector2.sub(p.pos, Vector2.mult(Vector2.proectTo(v, new Vector2(1, b)), k2));
                    if (Math.abs(b) == 0)
                    {
                        p.pos.y -= k1 * dl;
                        p.pos.x = p.posLast.x;
                    }
                    else
                    if (b > 0)
                        p.pos = Vector2.sum(p.pos, Vector2.mult(k1 * dl, (new Vector2(1, -1f / b)).normalize()));
                    else
                        p.pos = Vector2.sub(p.pos, Vector2.mult(k1 * dl, (new Vector2(1, -1f / b)).normalize()));
                }
            };
        };
         if (priority == RestrictionPriority.High){
             final Action<VerleEng.Particle> ac=highR;
             highR=new Action<VerleEng.Particle>() {
                 @Override
                 public void call(VerleEng.Particle p) {
                     ac.call(p);
                     r.call(p);
                 }
             };
         }
         if (priority == RestrictionPriority.Low){
             final Action<VerleEng.Particle> ac=lowR;
             lowR=new Action<VerleEng.Particle>() {
                 @Override
                 public void call(VerleEng.Particle p) {
                     ac.call(p);
                     r.call(p);
                 }
             };
         }
    }
}

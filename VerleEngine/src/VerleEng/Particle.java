package VerleEng;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;


public class Particle extends PhysicPrimitive{
    public enum Type{Static,Dynamic};
    private Type type;
    public Vector2 posLast, pos, a;
    public float mass,friction;
    public ArrayList<Particle> connectionList;
    public ArrayList<VerleEng.Joint> connections;
    public int xGroup, yGroup, collisionGroup, antiCollisionGroup;
    public Particle(Vector2 pos)
    {
        this.pos = pos;
        this.posLast = pos;
        a = new Vector2(0, 0);
        connectionList = new ArrayList<Particle>();
        connections = new ArrayList<VerleEng.Joint>();
        type = Type.Dynamic;
        mass = 1;
        friction=1;
    }

    public Particle(Vector2 pos, Vector2 posLast)
    {
        this.pos = pos;
        this.posLast = posLast;
        a = new Vector2(0, 0);
        connectionList = new ArrayList<Particle>();
        connections = new ArrayList<VerleEng.Joint>();
        type = Type.Dynamic;
        mass = 1;
    }

    public void integrate(float dt)
    {
        if (type == Type.Static)
        {
            pos = posLast;
            return;
        }
        Vector2 temp = pos;
        pos = Vector2.sum(Vector2.sub(Vector2.mult(pos, 2), posLast), Vector2.mult(a, dt * dt / 2));
        posLast=temp;
        a= Vector2.mult(a, 0);
    }

    public Type getType(){
        return type;
    }

    public void setType(Type type){
        this.type=type;
        if(type==Type.Static)
            mass=100000;
        else
            mass=1;
    }
}


package VerleEng;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 16.04.14
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class Struct extends  PhysicPrimitive {
    public Struct(Particle[] parts, Joint[] connections, CollisionLine[] lines)
    {
        this.parts = parts;
        this.connections = connections;
        this.lines=lines;
    }
    public Particle[] parts;
    public Joint[] connections;
    public CollisionLine[] lines;
}

package VerleEng;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 30.03.14
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 */
public abstract class Joint extends PhysicPrimitive{
    public Particle p1, p2;
    public float k;
    public abstract void connect();
}

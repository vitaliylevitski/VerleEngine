import MathVector.Vector2;
import VerleEng.*;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 18.06.14
 * Time: 9:45
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main(String[]args){
        VerleEngine world=new VerleEngine(new Vector2(0,-10));
        Struct s=Shapes.fromFile("Struct1.struct",new Vector2(0,0),1,0);
        world.add(s);
        Particle p1=new Particle(new Vector2(0,9)),p2=new Particle(new Vector2(0,0));
        world.add(p1,p2);
        RDistanceJoint j= new RDistanceJoint(p1,p2,1,1);j.length=10;
        world.add(j);
        Particle p=new Particle(new Vector2(0,0));
        p.setType(Particle.Type.Static);

    }
}

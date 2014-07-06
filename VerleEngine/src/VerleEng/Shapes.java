package VerleEng;

/**
 * Created with IntelliJ IDEA.
 * User: Vitaliy
 * Date: 17.04.14
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
import MathVector.Vector2;
import java.io.*;
import java.util.Arrays;

public abstract class Shapes {
     public static Struct rightTriangle(float size, Vector2 pos,float k,int collisionGroup,int antiCollisionGroup) {
         VerleEng.Particle p1=new VerleEng.Particle(Vector2.sum(pos, new Vector2(0, size)));
         VerleEng.Particle p2=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-size * 0.87f, -size / 2)));
         VerleEng.Particle p3=new VerleEng.Particle(Vector2.sum(pos, new Vector2(size * 0.87f, -size / 2)));
         VerleEng.DistanceJoint j1=new VerleEng.DistanceJoint(p1,p2,k);
         VerleEng.DistanceJoint j2=new VerleEng.DistanceJoint(p2,p3,k);
         VerleEng.DistanceJoint j3=new VerleEng.DistanceJoint(p3,p1,k);
         VerleEng.CollisionLine l1=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
         VerleEng.CollisionLine l2=new VerleEng.CollisionLine(j2,collisionGroup,antiCollisionGroup);
         VerleEng.CollisionLine l3=new VerleEng.CollisionLine(j3,collisionGroup,antiCollisionGroup);
         return new Struct(new VerleEng.Particle[]{p1,p2,p3},new VerleEng.DistanceJoint[]{j1,j2,j3},new VerleEng.CollisionLine[]{l1,l2,l3});
     }

    public static Struct rightTriangle(float size, Vector2 pos,float k,int collisionGroup,int antiCollisionGroup,float restitution) {
        VerleEng.Particle p1=new VerleEng.Particle(Vector2.sum(pos, new Vector2(0, size)));
        VerleEng.Particle p2=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-size * 0.87f, -size / 2)));
        VerleEng.Particle p3=new VerleEng.Particle(Vector2.sum(pos, new Vector2(size * 0.87f, -size / 2)));
        VerleEng.DistanceJoint j1=new VerleEng.RDistanceJoint(p1,p2,k,1-restitution);
        VerleEng.DistanceJoint j2=new VerleEng.RDistanceJoint(p2,p3,k,1-restitution);
        VerleEng.DistanceJoint j3=new VerleEng.RDistanceJoint(p3,p1,k,1-restitution);
        VerleEng.CollisionLine l1=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l2=new VerleEng.CollisionLine(j2,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l3=new VerleEng.CollisionLine(j3,collisionGroup,antiCollisionGroup);
        return new Struct(new VerleEng.Particle[]{p1,p2,p3},new VerleEng.DistanceJoint[]{j1,j2,j3},new VerleEng.CollisionLine[]{l1,l2,l3});
    }
    public static Struct box(float W,float H, Vector2 pos,float k,int collisionGroup,int antiCollisionGroup) {
        VerleEng.Particle p1=new VerleEng.Particle(Vector2.sum(pos, new Vector2(W, H)));
        VerleEng.Particle p2=new VerleEng.Particle(Vector2.sum(pos, new Vector2(W, -H)));
        VerleEng.Particle p3=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-W, -H)));
        VerleEng.Particle p4=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-W, H)));
        VerleEng.DistanceJoint j1=new VerleEng.DistanceJoint(p1,p2,k);
        VerleEng.DistanceJoint j2=new VerleEng.DistanceJoint(p2,p3,k);
        VerleEng.DistanceJoint j3=new VerleEng.DistanceJoint(p3,p4,k);
        VerleEng.DistanceJoint j4=new VerleEng.DistanceJoint(p4,p1,k);
        VerleEng.DistanceJoint j5=new VerleEng.DistanceJoint(p1,p3,k);
        VerleEng.CollisionLine l1=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l2=new VerleEng.CollisionLine(j2,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l3=new VerleEng.CollisionLine(j3,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l4=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
        return new Struct(new VerleEng.Particle[]{p1,p2,p3,p4},new VerleEng.DistanceJoint[]{j1,j2,j3,j4,j5},new VerleEng.CollisionLine[]{l1,l2,l3,l4});
    }

    public static Struct box(float W,float H, Vector2 pos,float k,int collisionGroup,int antiCollisionGroup,float restitution) {
        VerleEng.Particle p1=new VerleEng.Particle(Vector2.sum(pos, new Vector2(W, H)));
        VerleEng.Particle p2=new VerleEng.Particle(Vector2.sum(pos, new Vector2(W, -H)));
        VerleEng.Particle p3=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-W, -H)));
        VerleEng.Particle p4=new VerleEng.Particle(Vector2.sum(pos, new Vector2(-W, H)));
        VerleEng.DistanceJoint j1=new VerleEng.RDistanceJoint(p1,p2,k,1-restitution);
        VerleEng.DistanceJoint j2=new VerleEng.RDistanceJoint(p2,p3,k,1-restitution);
        VerleEng.DistanceJoint j3=new VerleEng.RDistanceJoint(p3,p4,k,1-restitution);
        VerleEng.DistanceJoint j4=new VerleEng.RDistanceJoint(p4,p1,k,1-restitution);
        VerleEng.DistanceJoint j5=new VerleEng.RDistanceJoint(p1,p3,k,1-restitution);
        VerleEng.CollisionLine l1=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l2=new VerleEng.CollisionLine(j2,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l3=new VerleEng.CollisionLine(j3,collisionGroup,antiCollisionGroup);
        VerleEng.CollisionLine l4=new VerleEng.CollisionLine(j1,collisionGroup,antiCollisionGroup);
        return new Struct(new VerleEng.Particle[]{p1,p2,p3,p4},new VerleEng.DistanceJoint[]{j1,j2,j3,j4,j5},new VerleEng.CollisionLine[]{l1,l2,l3,l4});
    }
    public static Struct circle(float r,Vector2 pos,float k1,float k2,int collisionGroup,int antiCollisionGroup){
        Particle[]parts=new Particle[(int)r*4+5];
        DistanceJoint[]connections=new DistanceJoint[(parts.length-1)*2];
        CollisionLine[]lines=new CollisionLine[parts.length-1] ;
        parts[0]=new Particle(pos);
        float dangle=2*(float)Math.PI/(parts.length-1);
        for(int i=1;i<parts.length;i++){
             parts[i]=new Particle(Vector2.sum(pos,new Vector2(r*(float)Math.cos(dangle*i),r*(float)Math.sin(dangle*i))));
            connections[i-1]=new DistanceJoint(parts[0],parts[i],k1);
        }
        for(int i=1;i<parts.length-1;i++){
            connections[parts.length-2+i]=new DistanceJoint(parts[i],parts[i+1],k2);
        }
        connections[connections.length-1]=new DistanceJoint(parts[parts.length-1],parts[1],k2);
        return new Struct(parts,connections,null);
    }

    public static Struct circle(float r,Vector2 pos,float k1,float k2,int collisionGroup,int antiCollisionGroup,float restitution){
        Particle[]parts=new Particle[(int)r*4+5];
        DistanceJoint[]connections=new DistanceJoint[(parts.length-1)*2];
        CollisionLine[]lines=new CollisionLine[parts.length-1] ;
        parts[0]=new Particle(pos);
        float dangle=2*(float)Math.PI/(parts.length-1);
        for(int i=1;i<parts.length;i++){
            parts[i]=new Particle(Vector2.sum(pos,new Vector2(r*(float)Math.cos(dangle*i),r*(float)Math.sin(dangle*i))));
            connections[i-1]=new RDistanceJoint(parts[0],parts[i],k1,1-restitution);
        }
        for(int i=1;i<parts.length-1;i++){
            connections[parts.length-2+i]=new RDistanceJoint(parts[i],parts[i+1],k2,1-restitution);
        }
        connections[connections.length-1]=new RDistanceJoint(parts[parts.length-1],parts[1],k2,1-restitution);
        return new Struct(parts,connections,null);
    }
    public static Struct circle2(float r,Vector2 pos,float k1,float k2,int collisionGroup,int antiCollisionGroup){
        Particle[]parts=new Particle[(int)r*4+5];
        DistanceJoint[]connections=new DistanceJoint[(parts.length-1)*2+(parts.length-1)];
        CollisionLine[]lines=new CollisionLine[parts.length-1] ;
        parts[0]=new Particle(pos);
        float dangle=2*(float)Math.PI/(parts.length-1);
        for(int i=1;i<parts.length;i++){
            parts[i]=new Particle(Vector2.sum(pos,new Vector2(r*(float)Math.cos(dangle*i),r*(float)Math.sin(dangle*i))));
            connections[i-1]=new DistanceJoint(parts[0],parts[i],k1);
        }
        for(int i=1;i<parts.length-1;i++){
            connections[parts.length-2+i]=new DistanceJoint(parts[i],parts[i+1],k2);
        }
        connections[(parts.length-1)*2-1]=new DistanceJoint(parts[parts.length-1],parts[1],k2);
        for(int i=1;i<parts.length-2;i++){
            connections[i+(parts.length-1)*2-1]=new DistanceJoint(parts[i],parts[i+2],k2);
        }
        connections[connections.length-2]=new DistanceJoint(parts[1],parts[parts.length-2],k2);
        connections[connections.length-1]=new DistanceJoint(parts[2],parts[parts.length-1],k2);
         for(int i=0;i<lines.length;i++) {
             lines[i]=new CollisionLine(connections[parts.length-1+i],collisionGroup,antiCollisionGroup);
         }

        return new Struct(parts,connections,lines);
    }

    public static Struct circle2(float r,Vector2 pos,float k1,float k2,int collisionGroup,int antiCollisionGroup,float restitution){
        Particle[]parts=new Particle[(int)r*4+5];
        DistanceJoint[]connections=new DistanceJoint[(parts.length-1)*2+(parts.length-1)];
        CollisionLine[]lines=new CollisionLine[parts.length-1] ;
        parts[0]=new Particle(pos);
        float dangle=2*(float)Math.PI/(parts.length-1);
        for(int i=1;i<parts.length;i++){
            parts[i]=new Particle(Vector2.sum(pos,new Vector2(r*(float)Math.cos(dangle*i),r*(float)Math.sin(dangle*i))));
            connections[i-1]=new RDistanceJoint(parts[0],parts[i],k1,1-restitution);
        }
        for(int i=1;i<parts.length-1;i++){
            connections[parts.length-2+i]=new RDistanceJoint(parts[i],parts[i+1],k2,1-restitution);
        }
        connections[(parts.length-1)*2-1]=new RDistanceJoint(parts[parts.length-1],parts[1],k2,1-restitution);
        for(int i=1;i<parts.length-2;i++){
            connections[i+(parts.length-1)*2-1]=new RDistanceJoint(parts[i],parts[i+2],k2,1-restitution);
        }
        connections[connections.length-2]=new RDistanceJoint(parts[1],parts[parts.length-2],k2,1-restitution);
        connections[connections.length-1]=new RDistanceJoint(parts[2],parts[parts.length-1],k2,1-restitution);
        for(int i=0;i<lines.length;i++) {
            lines[i]=new CollisionLine(connections[parts.length-1+i],collisionGroup,antiCollisionGroup);
        }

        return new Struct(parts,connections,lines);
    }
    public static Struct fromFile(String filepath,Vector2 pos,int collisionGroup,int antiCollisionGroup){
        Particle[]particles;
        Joint[] joints;
        CollisionLine[] collisionLines;
        try{
            File f=new File(filepath);
            boolean is=f.exists();
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            int particleCount=Integer.parseInt(reader.readLine());
            particles=new Particle[particleCount];
            for(int i=0;i<particleCount;i++){
               String[] params=reader.readLine().split(" ");
               particles[i]=new Particle(Vector2.sum(new Vector2(Float.parseFloat(params[0]),Float.parseFloat(params[1])),pos));
               particles[i].mass=Float.parseFloat(params[2]);
                if(Integer.parseInt(params[3])==0)
                    particles[i].setType(Particle.Type.Dynamic);
                else
                    particles[i].setType(Particle.Type.Static);
                particles[i].collisionGroup=collisionGroup;
                particles[i].antiCollisionGroup=antiCollisionGroup;
            }
            int jointCount=Integer.parseInt(reader.readLine());
            joints=new Joint[jointCount];
            for(int i=0;i<jointCount;i++){
                String[] params=reader.readLine().split(" ");
                switch (Integer.parseInt(params[0])){
                    case 0:
                        joints[i]=new DistanceJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]));
                        break;
                    case 1:
                        joints[i]=new RDistanceJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]),Integer.parseInt(params[4]));
                        break;
                    case 2:
                        break;
                    case 3:
                        joints[i]=new PositionJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]));
                        break;
                    case 4:
                        joints[i]=new LineJoint(particles[Integer.parseInt(params[1])],(DistanceJoint)joints[Integer.parseInt(params[2])],Integer.parseInt(params[3]),Integer.parseInt(params[4]));
                        break;

                }
            }
            int collisionLinesCount=Integer.parseInt(reader.readLine());
            collisionLines=new CollisionLine[collisionLinesCount];
            for(int i=0;i<collisionLinesCount;i++){
                String[] params=reader.readLine().split(" ");
                collisionLines[i]=new CollisionLine((DistanceJoint)joints[Integer.parseInt(params[0])],Integer.parseInt(params[1]),Integer.parseInt(params[2]));
            }
        }
        catch (Exception ex){ throw new RuntimeException("cannot load body"); }
        return new Struct(particles,joints,collisionLines);
    }

    public static Struct fromFile(String stringsToParse[],Vector2 pos,int collisionGroup,int antiCollisionGroup){
        Particle[]particles;
        Joint[] joints;
        int num=0;
        CollisionLine[] collisionLines;
        try{
            int particleCount=Integer.parseInt(stringsToParse[num]);
            num++;
            particles=new Particle[particleCount];
            for(int i=0;i<particleCount;i++){
                String[] params=stringsToParse[num].split(" ");
                num++;
                particles[i]=new Particle(Vector2.sum(new Vector2(Float.parseFloat(params[0]),Float.parseFloat(params[1])),pos));
                particles[i].mass=Float.parseFloat(params[2]);
                if(Integer.parseInt(params[3])==0)
                    particles[i].setType(Particle.Type.Dynamic);
                else
                    particles[i].setType(Particle.Type.Static);
                particles[i].collisionGroup=collisionGroup;
                particles[i].antiCollisionGroup=antiCollisionGroup;
            }
            int jointCount=Integer.parseInt(stringsToParse[num]);
            num++;
            joints=new Joint[jointCount];
            for(int i=0;i<jointCount;i++){
                String[] params=stringsToParse[num].split(" ");
                num++;
                switch (Integer.parseInt(params[0])){
                    case 0:
                        joints[i]=new DistanceJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]));
                        break;
                    case 1:
                        joints[i]=new RDistanceJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]),Integer.parseInt(params[4]));
                        break;
                    case 2:
                        break;
                    case 3:
                        joints[i]=new PositionJoint(particles[Integer.parseInt(params[1])],particles[Integer.parseInt(params[2])],Integer.parseInt(params[3]));
                        break;
                    case 4:
                        joints[i]=new LineJoint(particles[Integer.parseInt(params[1])],(DistanceJoint)joints[Integer.parseInt(params[2])],Integer.parseInt(params[3]),Integer.parseInt(params[4]));
                        break;

                }
            }
            int collisionLinesCount=Integer.parseInt(stringsToParse[num]);
            num++;
            collisionLines=new CollisionLine[collisionLinesCount];
            for(int i=0;i<collisionLinesCount;i++){
                String[] params=stringsToParse[num].split(" "); num++;
                collisionLines[i]=new CollisionLine((DistanceJoint)joints[Integer.parseInt(params[0])],Integer.parseInt(params[1]),Integer.parseInt(params[2]));
            }
        }
        catch (Exception ex){ throw new RuntimeException("cannot load body"); }
        return new Struct(particles,joints,collisionLines);
    }
}

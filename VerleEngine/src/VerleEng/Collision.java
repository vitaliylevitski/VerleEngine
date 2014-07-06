package VerleEng;

import MathVector.Vector2;

import java.util.ArrayList;
public class Collision{
    public ArrayList<Vector2.Position> positions;
    public Particle p;
    public boolean active;

    public Collision(Particle p){
        this.p = p;
        positions = new ArrayList<Vector2.Position>();
    }
}


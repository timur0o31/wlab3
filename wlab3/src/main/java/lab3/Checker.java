package lab3;

public class Checker {
    public Checker(){}
    public static boolean check(float x, float y, float r){
        return ((y<=0 && x<=0 && y>= -x - r) || (x<=0 && x>=-r && y>=0 && y<=r*0.5) || (x>=0 && y<=0 && x*x+y*y<=r*r));
    }
}

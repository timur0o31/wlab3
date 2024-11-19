package lab3;

public class Validator {
    public Validator() {}
    public static boolean validate(float x, float y, float r) {
        return (x >= -5 && x <= 5) && (y >= -3 && y <= 5) && (r >= 1 && r <= 3);
    }
}


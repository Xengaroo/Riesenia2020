public class Or extends Vyraz {
    Vyraz a;
    Vyraz b;
    String character_a;
    String character_b;
    Or(Vyraz x, Vyraz y) {
        if (x == null || y == null) zlyVstup();
        a = x;
        b = y;
        try {
            character_a = x.toString();
            character_b = y.toString();
        }
        catch (NullPointerException e) {
            zlyVstup();
        }

    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26 || a == null || b == null) zlyVstup();
        for (double item:interpretacia) if (item < 0 | item > 1) zlyVstup();
        double value_a = a.eval(interpretacia);
        double value_b = b.eval(interpretacia);

        return value_a + value_b - value_a*value_b;
    }

    @Override
    public String toString() {
        //if (a == null || b == null) zlyVstup();
        return "(" + character_a + " | " + character_b + ")";
    }
}

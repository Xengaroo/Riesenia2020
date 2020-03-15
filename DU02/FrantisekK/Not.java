public class Not extends Vyraz {
    char character;
    Vyraz a;
    Not(Vyraz x) {
        if (x == null) zlyVstup();
        a = x;
    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26 || a == null) zlyVstup();
        for (double item:interpretacia) if (item < 0 | item > 1) zlyVstup();
        return 1 - a.eval(interpretacia);
    }

    @Override
    public String toString() {
        //if (a == null) zlyVstup();
        return "-" + a.toString();
    }
}

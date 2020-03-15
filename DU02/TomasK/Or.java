public class Or extends Vyraz {
    private Vyraz x;
    private Vyraz y;

    public Or(Vyraz x, Vyraz y) {
        if (x == null || y == null) {
            zlyVstup();
        }
        this.x = x;
        this.y = y;
    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia == null || interpretacia.length != 26) {
            zlyVstup();
        }
        for (int i = 0; i < interpretacia.length; i++) {
            if (interpretacia[i] > 1.0 || interpretacia[i] < 0.0) {
                zlyVstup();
            }
        }
        return x.eval(interpretacia) + y.eval(interpretacia) - x.eval(interpretacia) * y.eval(interpretacia);
    }

    @Override
    public String toString() {
        return "(" + x.toString() + " | " + y.toString() + ")";
    }
}

public class Not extends Vyraz {
    private Vyraz x;

    public Not(Vyraz x) {
        if (x == null) {
            zlyVstup();;
        }
        this.x = x;
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
        return 1 - x.eval(interpretacia);
    }

    @Override
    public String toString(){
        return "-" + x.toString();
    }
}

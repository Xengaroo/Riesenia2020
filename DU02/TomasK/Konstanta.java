public class Konstanta extends Vyraz{
    private boolean value;

    public Konstanta(boolean hodnota) {
        this.value = hodnota;
    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia == null) {
            zlyVstup();
        }
        if (interpretacia.length != 26) {
            zlyVstup();
        }
        for (int i = 0; i < interpretacia.length; i++) {
            if (interpretacia[i] > 1.0 || interpretacia[i] < 0.0) {
                zlyVstup();
            }
        }

        return (this.value == true) ? 1.0 : 0.0;
    }

    @Override
    public String toString() {
        if (this.value) {
            return "TRUE";
        }
        return "FALSE";
    }
}

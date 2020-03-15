public class Premenna extends Vyraz{
    char meno;
    double value;

    public Premenna(char meno) {
        if (meno >= 'A' && meno <= 'Z') {
            this.meno = meno;
        } else {
            zlyVstup();
        }

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
        char znak = 'A';
        for (int i = 0; i < interpretacia.length; i++) {
            //System.out.println(this.meno + " " + interpretacia[i] + " " + znak);
            if (znak == this.meno) {
                this.value = interpretacia[i];
                return this.value;
            }
            znak++;
        }
        zlyVstup();
        return 0.0;
    }

    @Override
    public String toString() {
        return "" + meno;
    }
}

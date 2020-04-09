import java.util.Comparator;
import java.util.Objects;

public class Doktor{
    private final String meno;
    private final int vek;
    private final int prax;

    public Doktor(String meno, int vek, int prax) {
        if (meno == null || prax < 0 || vek < prax) {
            throw new IllegalArgumentException("Zle parametre pre doktora! Doktor(" + meno + "," + vek + "," + prax + ")");
        }
        this.vek = vek;
        this.meno = meno;
        this.prax = prax;
    }

    public int getVek() {
        return vek;
    }

    public String getMeno() {
        return meno;
    }

    public int getPrax() {
        return prax;
    }

    @Override
    public String toString() {
        return "(" + meno + ",v=" + vek + ",p=" + prax + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doktor doktor = (Doktor) o;
        return vek == doktor.vek &&
                prax == doktor.prax &&
                meno.equals(doktor.meno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meno, vek, prax);
    }

}

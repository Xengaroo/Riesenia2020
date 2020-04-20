import java.util.Objects;

public class Suradnica {
    int r, s;

    public Suradnica(int r, int s) {
        this.r = r;
        this.s = s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suradnica suradnica = (Suradnica) o;
        return r == suradnica.r &&
                s == suradnica.s;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, s);
    }

    @Override
    public String toString() {
        return "(" + r +"," + s + ")";
    }
}

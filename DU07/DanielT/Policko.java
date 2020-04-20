public abstract class Policko {

    String vlastnik;

    public Policko(String vlastnik) {
        this.vlastnik = vlastnik;
    }
}

class Cesta extends Policko {

    public Cesta(String vlastnik) {
        super(vlastnik);
    }

    @Override
    public String toString() {
        return "Cesta(" + vlastnik + ")";
    }
}

class Dedina extends Policko {

    public Dedina(String vlastnik) {
        super(vlastnik);
    }

    @Override
    public String toString() {
        return "Dedina(" + vlastnik + ")";
    }
}

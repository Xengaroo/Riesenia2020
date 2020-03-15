public class Premenna extends Vyraz {
    char character;
    Premenna(char ch) {
        if (ch < 'A' || ch > 'Z') zlyVstup();
        character = ch;
    }

    @Override
    public String toString() {
        //if (character > 'Z' || character < 'A') zlyVstup();
        return "" + character;
    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia.length > 26) zlyVstup();
        return interpretacia[character - 'A'];
    }
}

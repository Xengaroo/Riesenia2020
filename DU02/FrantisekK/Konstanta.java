public class Konstanta extends Vyraz {
    String type_of_konst;

    Konstanta(boolean b) {
        type_of_konst = b == true ? "TRUE" : "FALSE";
    }

//    Konstanta(double b) {
//        if (b != 0.0 && b != 1.0) zlyVstup();
//        type_of_konst = Math.abs(b - 1.0) < 0.001 ? "TRUE" : "FALSE";
//    }

    @Override
    public double eval(double[] interpretacia) {
        if (interpretacia.length > 26) zlyVstup();
        return type_of_konst.equals("TRUE") ? 1.0 : 0.0;
    }

    @Override
    public String toString() {
        //if (type_of_konst == null) zlyVstup();
        return type_of_konst;
    }
}

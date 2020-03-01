public class Papalasi {

    public static int SucetPola(int[] pole){
        int vysl = 0;
        for(int i:pole){
            vysl += i;
        }
        return vysl;
    }

    public static int musimKupit(int[] papalasi){
        Moja var = new Moja(papalasi);
        var.rek(0, papalasi.length, 0);
        return var.GetVysl() + 1;
    }

    public static void main(String[] args) {
        System.out.println(musimKupit(new int[]{4,4,4,4,4}));
    }
}

import java.util.stream.IntStream;

public class Papalasi {

    public static int musimKupit(int[] papalasi) {
        int countAllPolitics = IntStream.of(papalasi).sum();
        int value = countAllPolitics % 2 == 0 ? countAllPolitics/2-1:countAllPolitics/2;
        Temp temp = new Temp(papalasi, value);
        return temp.compute();
    }

    public static void main(String[] args) {
        System.out.println(musimKupit(new int[]{2,5,9,13})); //je  14+1, lebo najväčšia opozícia je 9+5 a jeden pre predsedu,
        System.out.println(musimKupit(new int[]{1,1,2,4,9,13})); //je  14+1 , lebo najväčšia opozícia je 9+4+1 a jeden pre predsedu,
        System.out.println(musimKupit(new int[]{2,3,5,7,11})); //je 13+1, lebo najväčšia opozícia je 11+2 a jeden pre predsedu,
        System.out.println(musimKupit(new int[]{4,4,4,4,4})); //je  8+1, lebo najväčšia opozícia je 4+4 a jeden pre predsedu,
        System.out.println(musimKupit(new int[]{3,5,7,9,11,13})); //je 23+1, lebo najväčšia opozícia je 11+9+3 a jeden pre predsedu
    }
}



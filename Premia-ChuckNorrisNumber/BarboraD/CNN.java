import static java.lang.Math.pow;

public class CNN {
    public static long cnn(){
        long cislo = 9;
        while (true) {
            cislo++;
            if (!prvocislo(cislo)) {
                continue;
            }
            if (!prvocislo(opacne(cislo))) {
                continue;
            }
            if (!palindrom(binarne(cislo))) {
                continue;
            }
            if (!palindrom(binarne(opacne(cislo)))){
            continue;
            }
            return cislo;
        }
    }

    public static boolean prvocislo(long cislo){
        int delitel = 0;
        for (int i = 1; i <= cislo; i++){
            if (cislo % i == 0){
                delitel++;
            }
        }
        if (delitel == 2){
            return true;
        }
        return false;
    }

    public static long opacne(long cislo){
        long c = 0;
        while (cislo > 0){
            c *= 10;
            c += cislo % 10;
            cislo /= 10;
        }
        return c;
    }

    public static String binarne(long cislo){
        String bin = "";
        int i = 0;
        while (pow(2, i) <= cislo){
            i++;
        }

        for (int j = i-1; j >= 0; j--){
            if(pow(2, j) <= cislo){
                bin += '1';
                cislo -= pow(2,j);
            }
            else{
                bin += '0';
            }
        }

        return bin;
    }

    public static boolean palindrom(String cislo){
        int l = cislo.length();
        for (int i = 0; i <= l / 2; i++){
            if (cislo.charAt(i) != cislo.charAt(l-1-i)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(cnn());
    }
}

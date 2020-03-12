public class Nasobok {

    public static boolean prime(int n){
        for(int i = 2; i < n; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    public static String jednotky(int n){
        String vysl = "";
        for(int i = 0; i < n; i++){
            vysl += "1";
        }
        return vysl;
    }

    public static String nasobok10(int n){
        if(n == 0){
            return "0";
        }
        String vysl = "";
        int cislo = n;
        int pocetjednotiek = 1;
        int delitel = 5;
        while(cislo % 2 == 0){
            vysl += "0";
            cislo /= 2;
        }
        while(cislo % 5 == 0){
            vysl += "0";
            cislo /= 5;
        }
        while(cislo % 9 == 0){
            pocetjednotiek *= 9;
            cislo /= 9;
        }
        if(cislo % 3 == 0){
            pocetjednotiek *= 3;
            cislo /= 3;
        }
        int k = 0;
        while(cislo != 1){
            if(cislo % delitel == 0){
                if(k == 0){
                    pocetjednotiek *= (delitel - 1);
                    k++;
                }else{
                    pocetjednotiek *= delitel;
                }
                cislo /= delitel;
            }else{
                k = 0;
                delitel++;
            }
        }
        return jednotky(pocetjednotiek) + vysl;
    }

    public static void main(String[] args) {
        /*for(int i = 1; i < 101; i++){
            System.out.println(i+"....."+nasobok10(i));
        }*/
        System.out.println(nasobok10(0));
    }
}

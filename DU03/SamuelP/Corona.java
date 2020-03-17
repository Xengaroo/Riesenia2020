public class Corona {
    public static int kolkoPrezijeme(String[] veci, int[] pocty, String[][][] obchod){
        int pocetVeci = 0;
        int dumPocetVeci = 0;
        int pocetNasobkovVeci = 0;
        int tyzdne = 999999999;

        for(int i= 0; i< veci.length; i++) {
            for (int j = 0; j < obchod.length; j++) {
                if (obchod[j] != null) {
                    for (int k = 0; k < obchod[j].length; k++) {
                        if (obchod[j][k] != null) {
                            for (int l = 0; l < obchod[j][k].length; l++) {
                                if (obchod[j][k][l] != null) {
                                    if (obchod[j][k][l].equals(veci[i])) {
                                        pocetVeci++;

                                    }
                                }
                            }
                        }
                    }
                }
            }


            dumPocetVeci = pocetVeci / pocty[i];


            if (dumPocetVeci <= tyzdne){
                tyzdne = dumPocetVeci;
            }
            pocetVeci = 0;
            pocetNasobkovVeci = 0;
        }


        return tyzdne;
    }

    public static void main(String[] args) {
        String[] veci = {"konzerva", "inzulin"};
        int[] pocty = {5, 1};

        String[][][] obchod = {
                {
                        {null      , "konzerva", "konzerva", null      , "vino", "konzerva"},
                        {"konzerva", "konzerva", "konzerva", "konzerva", null  , "vino", "konzerva"},
                        {"konzerva", null      , null      , "konzerva", "vino"}
                },
                null,
                {
                        null,
                        {"vino", "konzerva", "konzerva", null  , "konzerva", "konzerva", null},
                        {null  , "vino"    , "olej"    , "olej", null      , "vino"    , null  , null},
                        {"olej", null      , "vino"    , null  , "konzerva", null      , "vino", "olej"}
                },
                {}
        };

        System.out.println(kolkoPrezijeme(veci, pocty, obchod));

    }
}






  //  int i = 0;
  //  String str = "";
 //       while (veci[i] != ","){
 //               str += veci[i];
  //              }
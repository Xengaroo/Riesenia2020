public class Corona {
    public static int kolkoPrezijeme(String[] veci, int[] pocty, String[][][] obchod) {
        int[] poctyObchod = new int[veci.length];

        if(obchod == null) return 0;
        for(int i = 0; i <  obchod.length; i++) {
            if(obchod[i] == null) continue;
            for(int j = 0; j < obchod[i].length; j++) {
                if(obchod[i][j] == null) continue;
                for(int k = 0; k < obchod[i][j].length; k++) {
                    if(obchod[i][j][k] != null) {
                        for(int l = 0; l < veci.length; l++) {
                            if(obchod[i][j][k].equals(veci[l])) {
                                poctyObchod[l]++;
                            }
                        }
                    }
                }
            }
        }

        int min = 0;
        for(int i = 0; i < veci.length; i++) {
            if(min > poctyObchod[i] / pocty[i] || i == 0) {
                min = poctyObchod[i] / pocty[i];
            }
        }

        return min;
    }

    public static void main(String[] args) {
        /*String[] veci = {"chlieb", "muka", "pivo", "olej"};
        int[] pocty = {2, 3, 5, 1};

        String[][][] obchod = {
                {
                        {null  , "chlieb", "pivo", null    , "olej", null  , "muka", "chlieb"},
                        {"pivo", "cukor" , "pivo", null    , null  , "muka", "chlieb"},
                        {"pivo", null    , null  , "chlieb", "muka", null  , null  , "pivo"}
                },
                {
                        {"muka", null  , null   , "pivo", null    , "cukor", "pivo"},
                        {null  , "muka", "cukor", "muka", "chlieb", "olej" , null, "chlieb"},
                        {"pivo", null  , "muka" , null  , null    , "pivo" , null}
                },
                null,  // chyba regal
                {
                        {"muka", null  , "chlieb", null  , "pivo", "pivo", null},
                        null,  // chyba policka
                        {null  , "muka", "olej"  , "olej", null  , "muka", null, null}
                }
        };

        System.out.println(kolkoPrezijeme(veci, pocty, obchod));*/
        // Odpoved je 2. Vieme nakupit 7 chlebov, 10 muk, 11 piv, 4 oleje - ale z 11 piv prezijeme len dva tzydne.

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
        // Odpoved je 0. Vieme sice nakupit 15 chlebov, ale ziaden inzulin, preto neprezijeme ani tyzden.
    }
}

public class Corona {
    public static int kolkoPrezijeme(String[] veci, int[] pocty, String[][][] obchod) {
        int velkost = veci.length;
        int[] inventura = new int[velkost];

        if (obchod == null) return 0;
        for (int i = 0; i < obchod.length; i++) {
            if (obchod[i] == null) continue;////
            for (int j = 0; j < obchod[i].length; j++) {
                if (obchod[i][j] == null) continue;
                for (int k = 0; k < obchod[i][j].length; k++) {
                    if (obchod[i][j][k] == null) continue;
                    for (int v = 0; v < velkost; v++) {
                        if (obchod[i][j][k].equals(veci[v])) inventura[v] += 1; ///
                    }
                }
            }
        }

        int mininum = Integer.MAX_VALUE;
        for (int i = 0; i < velkost; i++) {
            int pocet = inventura[i] / pocty[i];
            if (pocet < mininum) mininum = pocet;
        }

        return mininum;
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

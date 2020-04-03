public class VrutkyStrategia {
    /** Treba minimalizovat najhorsi mozny pocet spytani sa, ci je cislo vacsie. Tym, ze iba 2 krat mozem
     *  dostat zapornu odpoved na otazku ci je cislo vacsie, musim sa po prvej zapornej odpovedi pytat
     *  postupne od cisla, na ktore som sa pytal minule. Najhorsi mozny pripad je teda vtedy, ked cislo na ktore
     *  som dostal prvu zapornu odpoved je hladanym cislom. Vtedy sa spytam presne tolko otazok, kolko bol
     *  posun z minuleho cisla. Avsak za kazdy posun sa spytam tiez jednu otazku. Cize posun by mal byt vzdy
     *  o jedno menej nez predtym. Cize od konca by som sa pytal na cisla n, n - 1, n - 2, n - 3, ..., cize
     *  posun bude od konca 1, 2, 3, 4, ... Ako vsak zistit zaciatocny posun? Potrebujem prejst n cisel. Tym padom
     *  suma vsetkych posunov musi byt vacsia alebo rovna n. Cize - x + x - 1 + ... 2 + 1 = n. To sa da upravit
     *  na tvar (x * (x + 1)) / 2 = n. Riesenim je teda kladny koren kvadratickej rovnice (x^2 + x - 2n = 0) - cize
     *  takto: x = (odmocnina(1 + 8 * n) - 1) / 2.
     */

    public static void strategia(Vrutky m) {
        int n = m.doKolkoHrame();
        int posun = (int) Math.ceil((Math.sqrt(1 + 8 * n) - 1.0) / 2.0);
        int cislo = posun;

        while (posun > 0) {
            if ( ! m.jeVacsieAko(cislo)) {
                break;
            }
            posun--;
            cislo += posun;
        }

        int max = cislo;
        if (posun == 0) {
            max = n;
        }
        cislo -= (posun - 1);

        while (cislo < max) {
            if ( ! m.jeVacsieAko(cislo)) {
                m.jetoCislo(cislo);
                return;
            }
            cislo++;
        }

        m.jetoCislo(max);
    }


    public static void main(String[] args) {
        Vrutky m;
        for (int i = 0; i < 1000; i++) {
            m = new Vrutky();
            strategia(m);
            if ( ! m.uhadolCislo()) {
                System.out.println("oof");
            }
        }
        System.out.println("good");
    }

}

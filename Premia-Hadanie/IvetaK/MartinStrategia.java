public class MartinStrategia {

    public static void main(String[] args) {
        strategia(new Martin());
    }

    public static void strategia(Martin m) {
        int n = m.doKolkoHrame();
        int mojTip = m.doKolkoHrame() / 2;

        int hornaHranica = m.doKolkoHrame();
        int dolnaHranica = 0;


        while (true) {
            if (hornaHranica - dolnaHranica == 0) {
                m.jetoCislo(hornaHranica);
                break;
            }

            if (!m.jeVacsieAko(mojTip)) {
                if (mojTip < hornaHranica) {
                    hornaHranica = mojTip;
                }


                mojTip = dolnaHranica + (hornaHranica - dolnaHranica)/2;
            }

            else {

                if (mojTip >= dolnaHranica) {
                    dolnaHranica = mojTip + 1;
                }
                mojTip = dolnaHranica + (hornaHranica - dolnaHranica)/2;
            }
        }
    }


    }




   /*  public static void strategia(Martin m) {
         int n = m.doKolkoHrame();
         int pokusy = 1;
         for (;;) {


             int dalsiPokus = 1+ pokusy;

             if (!m.jeVacsieAko(dalsiPokus)) {
                 m.jetoCislo(dalsiPokus);

                 break;
             }



             pokusy ++;


             if (dalsiPokus > n)
                 break;
         }
     }*/


public class Mince {
    /** Strategia: Zrataj sucet cisel na parnych a neparnych miestach. Zober z konca ak je vacsi sucet
     *  cisel na parnych miestach, inak zober zo zaciatku.
     */

    public static boolean zoberPrvu(int[] mince) {
        int parne = 0;
        int neparne = 0;

        for (int i = 0; i < mince.length; i++) {
            if (i % 2 == 1) {
                parne += mince[i];
            }
            else {
                neparne += mince[i];
            }
        }

        return neparne > parne;
    }
}

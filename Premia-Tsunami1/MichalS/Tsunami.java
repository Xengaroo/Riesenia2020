public class Tsunami {
    public static long plochaVody(int[] pohorie) {
        if (pohorie == null || pohorie.length == 0) return 0;

        long plocha = 0;
        long aktual_plocha = 0;
        int aktual_max = pohorie[0];
        int max_index = 0;

        for (int i = 1; i < pohorie.length; i++) {
            if (pohorie[i] < aktual_max) {
                aktual_plocha += aktual_max - pohorie[i];
            }
            else if (pohorie[i] > aktual_max) {
                aktual_max = pohorie[i];
                max_index = i;
                plocha += aktual_plocha;
                aktual_plocha = 0;
            }
            else {
                max_index = i;
            }
        }

        if (max_index == pohorie.length - 1) {
            return plocha + aktual_plocha;
        }

        aktual_max = pohorie[pohorie.length - 1];
        aktual_plocha = 0;

        for (int i = pohorie.length - 1; i > max_index; i--) {
            if (pohorie[i] < aktual_max) {
                aktual_plocha += aktual_max - pohorie[i];
            }
            else if (pohorie[i] > aktual_max) {
                aktual_max = pohorie[i];
                plocha += aktual_plocha;
                aktual_plocha = 0;
            }
        }

        return plocha + aktual_plocha;
    }
}

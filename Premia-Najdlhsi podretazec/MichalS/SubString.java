public class SubString {
    public static String najdlhsiPodretazec(String str, int k) {
        if (str == null || k < 0) return null;
        if (k == 0) return "";

        final int DLZKA_STR = str.length();
        int dlzkaNaj = 1;
        int indexNaj = 0;
        int dlzka;
        int i = 0;
        String znak;
        StringBuilder s;

        while (i < DLZKA_STR - dlzkaNaj) {
            dlzka = 0;
            s = new StringBuilder();
            for (int j = i; j < DLZKA_STR; j++) {
                znak = "" + str.charAt(j);
                if (s.indexOf(znak) == -1) {
                    if (s.length() >= k) {
                        if (dlzka > dlzkaNaj) {
                            indexNaj = i;
                            dlzkaNaj = dlzka;
                        }
                        break;
                    }
                    s.append(znak);
                }
                dlzka++;
            }
            if (dlzka > dlzkaNaj) {
                indexNaj = i;
                dlzkaNaj = dlzka;
            }
            i++;
        }

        return str.substring(indexNaj, indexNaj + dlzkaNaj);
    }
}

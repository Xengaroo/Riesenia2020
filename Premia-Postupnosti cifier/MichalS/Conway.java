public class Conway {
    public static final String fixPoint = "22";

    public static String iteracia(String str, int k) {
        if (str == null || k < 0) return null;
        if (str.equals("") || k == 0) return str;

        StringBuilder s = new StringBuilder(str);
        StringBuilder s2;
        int count;
        char current;

        for (int i = 0; i < k; i++) {
            s2 = new StringBuilder();
            count = 0;
            current = s.charAt(0);

            for (int j = 0; j < s.length(); j++) {
                if (current == s.charAt(j)) {
                    count++;
                    continue;
                }
                s2.append(count);
                s2.append(current);
                count = 1;
                current = s.charAt(j);
            }

            s2.append(count);
            s2.append(current);
            s = s2;
        }

        return s.toString();
    }

    public static int dlzka(String str, int k) {
        if (str == null || k < 0) return 0;
        if (str.equals("") || k == 0) return 0;

        StringBuilder s = new StringBuilder(str);
        StringBuilder s2;
        int count;
        char current;

        for (int i = 0; i < k; i++) {
            s2 = new StringBuilder();
            count = 0;
            current = s.charAt(0);

            for (int j = 0; j < s.length(); j++) {
                if (current == s.charAt(j)) {
                    count++;
                    continue;
                }
                s2.append(count);
                s2.append(current);
                count = 1;
                current = s.charAt(j);
            }

            s2.append(count);
            s2.append(current);
            s = s2;
        }

        return s.length();
    }
}

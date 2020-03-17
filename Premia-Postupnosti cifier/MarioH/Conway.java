public class Conway {
    public static String iteracia(String str, int k) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < k; i++) {
            int count = 1;
            for (int j = 0; j < str.length(); j++) {
                if(j<str.length()-1 && str.charAt(j+1) == str.charAt(j)) {
                    count++;
                    continue;
                }
                sb.append("" + count + str.charAt(j));
                count = 1;
            }
            str = sb.toString();
            sb.delete(0, sb.length());
        }
        return str;
    }

    public static int dlzka(String str, int k) {
        return iteracia(str, k).length();
    }

    public static final String fixPoint = "22";

    public static void main(String[] args) {
        System.out.println(iteracia("1221", 60));
    }
}

import java.util.HashSet;
import java.util.Set;

class SubString {
    public static String najdlhsiPodretazec(String str, int k){
        String vysl;
        if(k == 0) return "";
        int zac = 0;
        int kon = 0;

        Set<Character> set = new HashSet<>();
        int[] pole = new int[128];
        int j = 0;
        int prvok;
        for (int i = 0; i < str.length(); i++) {
            set.add(str.charAt(i));
            pole[str.charAt(i)] += 1;
            while (set.size() > k) {
                prvok = --pole[str.charAt(j)];
                if (prvok == 0){
                    set.remove(str.charAt(j));}
                j++;
            }
            if(kon-zac < i-j){
                zac = j;
                kon = i;
            }
        }

        vysl = str.substring(zac,kon+1);
        return vysl;
    }

    public static void main(String[] args) {
        System.out.println(najdlhsiPodretazec("abaaacbbc" , 2));
    }
}

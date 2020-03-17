public class Conway {
    public static final String fixPoint = "22";
    public static String iteracia(String str, int k){
        String stary = str;
        StringBuilder novyy = new StringBuilder("");
        int count = 1;
        for(int i = 0; i < k; i++){
            for(int j = 1; j < stary.length(); j++){
                if(stary.charAt(j-1) == stary.charAt(j)){
                    count++;
                }else{
                    novyy.append(count);novyy.append(stary.charAt(j-1));
                    count = 1;
                }
            }
            novyy.append(count);novyy.append(stary.charAt(stary.length()-1));
            stary = novyy.toString(); novyy = new StringBuilder("");; count = 1;
        }
        return stary;
    }

    public static int dlzka(String str, int k){
        return iteracia(str, k).length();
    }

    public static void main(String[] args) {
        /*for(int i = 1; i < 15; i++){
            System.out.println(iteracia("2222",i).length());
        }
        System.out.println(iteracia("9",6));
        System.out.println(dlzka("1227891",40));*/
        System.out.println(iteracia("22",20));
    }
}

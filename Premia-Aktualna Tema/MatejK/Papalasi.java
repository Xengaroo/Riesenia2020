public class Papalasi {
    public static int musimKupit(int[] papalasi){
        int n = papalasi.length;
        int celkovySucet = 0;
        for (int i = 0; i < n; i++){
            celkovySucet += papalasi[i];
        }
        int polovica = celkovySucet % 2 == 0? celkovySucet / 2 : (celkovySucet+1) / 2;

        int max = 0;
        for (int i = 0; i < (1<<n); i++){
            int sucetPodmnoziny = 0;
            for(int j=0; j < n; j++){
                if((i & (1 << j)) > 0){
                    sucetPodmnoziny += papalasi[j];
                }
                if(sucetPodmnoziny > polovica) break;
                if(sucetPodmnoziny < polovica && sucetPodmnoziny > max){
                    max = sucetPodmnoziny;
                }
            }
        }
        return max + 1;
    }

    public static void main(String[] args) {
        System.out.println(musimKupit(new int[]{4,4,4,4,4}));
    }
}

//https://www.geeksforgeeks.org/finding-all-subsets-of-a-given-set-in-java/
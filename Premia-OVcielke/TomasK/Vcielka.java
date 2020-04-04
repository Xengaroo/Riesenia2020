import java.util.Arrays;
import java.util.List;

public class Vcielka {
    public static int goHome(String path) {
        List<String> cesta = Arrays.asList(path.split(","));
        System.out.println(cesta.toString());

        int i = 0;
        int j = 0;

        for (String smer : cesta) {
            switch (smer) {
                case "ne" :
                    i++;
                    break;
                case "se" :
                    i++;
                    j--;
                    break;
                case "s" :
                    j--;
                    break;
                case "sw" :
                    i--;
                    break;
                case "nw" :
                    i--;
                    j++;
                    break;
                case "n" :
                    j++;
                    break;
            }
        }
        System.out.println(i + " " + j);
        int kroky = 0;
//        i = Math.abs(i);
//        j = Math.abs(j);
        while (i != 0 || j != 0) {
            if (i > 0 && j < 0) {
                i--;
                j++;

            } else
            if (i < 0 && j > 0) {
                i++;
                j--;

            } else
            if (i > 0) {
                i--;

            } else
            if (i < 0) {
                i++;

            } else
            if (j > 0) {
                j--;

            } else
            if (j < 0) {
                j++;
            }
            kroky++;


        }
//        System.out.println("Kroky: " + kroky);
        return kroky;
    }

    public static void main(String[] args) {
        Vcielka.goHome("ne,ne,n");
        Vcielka.goHome("ne,nw,sw,s,se,ne");
        Vcielka.goHome("ne,ne,ne");
        Vcielka.goHome("ne,ne,sw,sw");
        Vcielka.goHome("ne,ne,s,s");
        Vcielka.goHome("se,sw,se,sw,sw");
    }
}

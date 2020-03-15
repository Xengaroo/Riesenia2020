public class Csi {
    public static double[][] enhance(double[][] array) {
        int pocet_r = 2 * array.length - 1;
        int pocet_s = 2 * array[0].length - 1;
        double[][] pole = new double[pocet_r][pocet_s];

        for (int r = 0; r < pocet_r; r++) {
            if (r % 2 == 0) {
                for (int s = 0; s < pocet_s; s++) {
                    if (s % 2 == 0) {
                        pole[r][s] = array[r/2][s/2];
                    }
                    else {
                        double a = array[r/2][(s-1)/2];
                        double b = array[r/2][(s+1)/2];
                        pole[r][s] = (a+b)/2;
                    }

                }
            }

            else {
                for (int s = 0; s < pocet_s; s++) {
                    if (s % 2 == 0) {
                        double a = pole[r-1][s];
                        double b = array[(r+1)/2][s/2];
                        pole[r][s] = (a+b)/2;
                    }
                    else {
                        double a = pole[r-1][s];
                        double b = (array[(r+1)/2][(s-1)/2] + array[(r+1)/2][(s+1)/2]) / 2;
                        pole[r][s] = (a+b)/2;
                    }

                }
            }
        }

        return pole;
    }

    public static void main(String[] args) {
        double[][] p1 = {{1.00,2.00,3.00,4.00},{5.00,6.00,7.00,8.00},{9.00,10.00,11.00,12.00}};
        double[][] p2 = enhance(p1);
        for(double[] pole : p2) {
            for (double prvok : pole) {
                System.out.print(prvok + " ");
            }
            System.out.print("\n");
        }
    }
}

public class Csi {
    public static double[][] enhance(double[][] array){
        double[][] result = new double[array.length * 2 - 1][array[0].length * 2 - 1];
        for (int i = 0;i < array.length;i++){
            for (int j = 0;j < array[i].length;j++){
                result[i*2][j*2] = array[i][j];
            }
        }
        for (int i = 0;i < result.length;i+=2){
            for (int j = 1;j < result[i].length;j+=2) {
                result[i][j] = (result[i][j-1] + result[i][j+1] )/ 2;
            }
        }
        for (int i = 1; i < result.length;i+=2){
            for (int j = 0;j<result[i].length;j++){
                result[i][j] = (result[i-1][j] + result[i+1][j] )/ 2;
            }
        }


        return result;
    }
}

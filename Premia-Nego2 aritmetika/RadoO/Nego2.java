public class Nego2 {
    public static String sucet(String a, String b){
        int pomp = 0;
        int pomm = 0;
        int c = 0;
        if(a.length() > b.length()){
            c = b.length();
        }else{
            c = a.length();
        }
        int aa = a.length()-1;
        int bb = b.length() -1;
        int pomc = 0;
        String p;
        StringBuffer res1 = new StringBuffer();

        for(int i =c-1;i>=0;i--){
            if(a.charAt(aa) == '1' && b.charAt(bb) == '1'){

                pomc = 0 - pomm + pomp;
                if(pomc < 0){
                    pomc *= -1;
                }
                res1.append(Integer.toString(pomc));
                if(pomm == 1 && pomp == 0){
                    pomp = 1;
                }else{
                    pomp = 0;
                }
                pomm = 1;
            }
            if((a.charAt(aa) == '1' && b.charAt(bb) == '0') || (a.charAt(aa) == '0' && b.charAt(bb) == '1')){

                pomc = 1 - pomm + pomp;
                if(pomc > 1){
                    pomc = 0;
                }
                res1.append(Integer.toString(pomc));
                if(pomm == 0 && pomp == 1){
                    pomm = 1;
                }else{
                    pomm = 0;
                }
                pomp = 0;
            }
            if(a.charAt(aa) == '0' && b.charAt(bb) == '0'){
               // System.out.println(pomm);

                pomc = 0 - pomm + pomp;
                if(pomc < 0){
                    pomc = 1;
                }
                res1.append(Integer.toString(pomc));
                if(pomm == 1 && pomp == 0){
                    pomp = 1;
                }else{
                    pomp = 0;
                }
                if(pomm == 0 && pomp == 1){
                    pomm = 1;
                }else{
                    pomm = 0;
                }

            }
            aa--;
            bb--;
        }

        if(aa >= 0){
            int q = aa;
            for(int i = 0; i<= q;i++){
                if(a.charAt(aa) == '1'){
                    pomc = 1 - pomm + pomp;
                    if(pomc > 1){
                        pomc = 0;
                    }
                    res1.append(Integer.toString(pomc));
                    if(pomm == 0 && pomp ==1){
                        pomm = 1;
                    }else{
                        pomm = 0;
                    }
                    pomp = 0;
                }
                if(a.charAt(aa) == '0'){
                    pomc = 0 - pomm + pomp;
                    if(pomc < 0){
                        pomc = 1;
                    }
                    res1.append(Integer.toString(pomc));
                    if(pomm == 1 && pomp ==0){
                        pomp = 1;
                    }else{
                        pomp = 0;
                    }
                    pomm = 0;
                }
                aa--;
            }
        }

        if(bb >= 0){
            int q = bb;
            for(int i = 0; i<= q;i++){

                if(b.charAt(bb) == '1'){
                    pomc = 1 - pomm + pomp;
                    if(pomc > 1){
                        pomc = 0;
                    }
                    res1.append(Integer.toString(pomc));
                    if(pomm == 0 && pomp ==1){
                        pomm = 1;
                    }else{
                        pomm = 0;
                    }
                    pomp = 0;
                }
                if(b.charAt(bb) == '0'){
                    pomc = 0 - pomm + pomp;
                    if(pomc < 0){
                        pomc = 1;
                    }
                    res1.append(Integer.toString(pomc));
                    if(pomm == 1 && pomp ==0){
                        pomp = 1;
                    }else{
                        pomp = 0;
                    }
                    pomm = 0;
                }
                bb--;
            }
        }

        if(pomm == 1 && pomp == 0){

            res1.append("11");

            pomm = 0;
        }
        if(pomm == 0 && pomp ==1){

            res1.append("1");

            pomp = 0;
        }
        if(pomm == 1 && pomp ==1){
            res1.append("0");
            pomm = 0;
            pomp = 0;
        }
        int i = res1.length() - res1.lastIndexOf("1");
    res1.reverse();
    String res = res1.substring(i-1);
    return res;
    }
    public static String opacne(String a){
        return sucet(a,a+"0");
    }
    public static void main(String[] args) {
        System.out.println(sucet("0101","01"));
        System.out.println(sucet("0101011111101101110001101011101000101001111111011101110001000000101100001011011111010101010000110000110111010101111100101010011100011111111010010001111111011100101110000111100001011000011100010000110000110111111111010111000001111010111110010000111011001010011110011010000111000001010100010011001100110000011111000111001000111011000101110001100011000001111101101001001101010101000110100000011001100011110100011101000000000111111000110010110111111100100100110000011010100011111001001001011110111001100101111001010101101100000111110001011110100011010100011110110100010010010010001010010110101101011101101110111110111001110001110101111100000000001101010010110101001011011110101010001111010111000001011010111111000110110100011000100101101010010110101000001000011110110110011100110110000111100000110011000001101110011110100010011010011011010010111010111111110101110100110001001000110001101111101100101001101001101000110110000001010101010010110101111000100100000000110011100110111111101000001000011110011011100000001000001010101100","0111111010111110100100100010111011100011101110111110100111111111100111100111101110000010001001001010110111001100001100110001101000111000011010010010000001010111010010011100110011110100111010011010011100010001001100111110111101101101010110100011110000110111111001001101101111010110111101101110011011000111010010001101000011101011101011110110100010111000001010010011010000010100100100001111000101110110110010011100001010111100000111011100101000111110100101101100001001111111101000001100100011110111100111010001110110110111101110001101110011011101001011001000100100101000011110000010001011011110010110100001110010011010101011100101111011000001111101001101010100100011101011010111000001101111010000101110111100010111100010000111111100101111010101010001101011110000000101111010000110110110111111010001100111011011011100000010010101101000000011001110100111010000011011001111010100010100111101101101101100010101010100110110001010110011010011111001000111010011100000001001101001111010100101011110000110101001101011001110111000001111"));
    }
}

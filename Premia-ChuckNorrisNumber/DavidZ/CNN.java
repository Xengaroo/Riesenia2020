public class CNN {
    public static int cnn(){
        int number = 10;
        while (true){
            if(primeNumber(number)){
               String bNumber = Integer.toBinaryString(number);
               if(bNumber.equals(reverseBinaryNumber(bNumber))){
                   String bNumber2 = Integer.toBinaryString(reverseNumber(number));
                   if(bNumber2.equals(reverseBinaryNumber(bNumber2))){
                       return number;
                   }
               }
            }
            number++;
        }
    }

    public static int reverseNumber(int number){
        int rNumber = 0;
        while(number != 0){
            int digit = number % 10;
            rNumber = rNumber * 10 + digit;
            number /= 10;
        }
        return rNumber;
    }

    public static String reverseBinaryNumber(String number){
        String result = "";
        for(int i = number.length()-1; i >= 0; i--) {
            result += number.charAt(i);
        }
        return result;
    }

    public static String binaryNumber(int number){
        String result = "";
        while (number != 0){
            result += Integer.toString(number % 2);
            number /= 2;
        }
        return result;
    }

    public static boolean primeNumber(int num){
        boolean flag = false;
        for(int i = 2; i <= num/2; ++i)
        {
            if(num % i == 0)
            {
                flag = true;
                break;
            }
        }
        if(!flag){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        //System.out.println(reverseBinaryNumber("1100"));
        System.out.println(cnn());
    }
}

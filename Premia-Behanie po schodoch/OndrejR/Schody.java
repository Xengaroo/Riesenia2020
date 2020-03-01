public class Schody {
    //https://www.geeksforgeeks.org/count-ways-to-reach-the-nth-stair-using-any-step-from-the-given-array/
    public static long pocet12(int n) {
        int a = 1;
        int b = 1;
        int c = 0;
        for(int i = 0;i < n;i++){
            c = a+b;
            a = b;
            b=c;
        }
        return a;
    }
    public static long pocet123(int n) {
        int a = 1;
        int b = 2;
        int c = 4;
	int pom = 0;
        for(int i = 0;i < n-1;i++){
            pom = a+b+c;
            a = b;
            b = c;
	    c = pom;
        }
        return a;
    }
    public static int pocet(int n, int[] kroky){
        
       
	    int arr[]  = new int[n+1];
	    arr[0] = 1;
 
	    if(n == 0){
		    return 1;
	    }
    	int skaceme = 0 ;
    	for(int i = 1 ;i<n+1 ;i++){ 
    		skaceme = 0 ;
    		for(int var:kroky){
    			
    			if(i - var >= 0){
    				skaceme  += arr[i-var];
    			}
    			arr[i] = skaceme;
    		} 
    	}
    	return arr[n] ;
        	
    }
    public static void main(String[] args) {
        //System.out.println(fib(5));
    }

}
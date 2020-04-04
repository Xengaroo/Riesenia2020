public class Vcielka {

    public static int goHome(String path){
        int stlpec = 0;
        int suradnica2 = 0;
        String[] pole = path.split(",");
        for(String i:pole){
            switch(i){
                case "n" :  suradnica2--; break;
                case "s" :  suradnica2++; break;
                case "ne" : stlpec++; break;
                case "sw" : stlpec--; break;
                case "nw" : stlpec--; suradnica2--; break;
                case "se" : stlpec++; suradnica2++; break;
                default: continue;
            }
        }
        if(stlpec >= 0 && suradnica2 >= 0){
            if(stlpec > suradnica2) return stlpec;
            return suradnica2;
        }
        if(stlpec <= 0 && suradnica2 <= 0){
            if(stlpec < suradnica2) return -stlpec;
            return -suradnica2;
        }
        return Math.abs(stlpec) + Math.abs(suradnica2);
    }
}

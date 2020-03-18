public class MartinStrategia {

    public static void main(String[] args) {
        strategia(new Martin());
    }
    public static void strategia(Martin m) {
        int n = m.doKolkoHrame();
        int floor = 1;
        int ceil = n;
        while(true) {
//            System.out.println(floor + "." + ceil);
            if (!m.jeVacsieAko( (floor+ceil)/2 )) {
                ceil = (floor+ceil)/2;
            } else {
                floor = (floor+ceil)/2+1;
            }
            if ( floor == ceil ) {
                m.jetoCislo(ceil);
                    break;
            }
        }
        System.out.println(m.kolkoOtazok());
    }
}

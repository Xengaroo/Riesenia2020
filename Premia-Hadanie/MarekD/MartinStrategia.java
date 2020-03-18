public class MartinStrategia {

    public static void main(String[] args) {
        strategia(new Martin());
    }
    public static int stred(int zac, int kon){
        return (kon + zac) / 2;
    }
    public static void strategia(Martin m) {
        int kon = m.doKolkoHrame();
        int zac = 0;
        int stred = stred(zac, kon);
        boolean pokus = false;
        while (kon - zac > 1) {
            stred = stred(zac, kon);
            if (m.jeVacsieAko(stred)){
                zac = stred;
                pokus = true;
            }
            else{
                kon = stred;
                pokus = false;
            }
        }
        if (pokus){
            m.jetoCislo(stred + 1);
        }
        else{
            m.jetoCislo(stred);
        }
        System.out.println(m.kolkoOtazok());
    }
}

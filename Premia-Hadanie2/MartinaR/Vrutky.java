import java.util.Random;

final class Vrutky {

    Random rnd = new Random();
    private int n = 100;
    private int x = 100;
    private boolean uhadol = false;
    private int pocetTipov = 0;
    private int pocetOtazok = 0;
    private int pocetFalse = 0;
    public Vrutky() {
        n = 10+rnd.nextInt(300);
        x = 1+rnd.nextInt(n);               // 1..n
        System.out.println("hrame 1.."+n);
        uhadol = false;
        pocetTipov = 0;
        pocetOtazok = 0;
        pocetFalse = 0;
    }
    public int doKolkoHrame() {
        return n;
    }
    public boolean jeVacsieAko(int m) {
        System.out.println("jeVacsieAko "+m + " je " + (x > m) );
        pocetOtazok++;
        if (!(x > m)) pocetFalse++;
        return x > m;
    }
    public void jetoCislo(int m) {
        if (pocetTipov > 0) {
            System.out.println("jetoCislo " + m + " ... sorry, uz si si tipol");
            uhadol = false;
        } else {
            System.out.println("jetoCislo " + m + " je " + (x == m));
            uhadol = (x == m);
            pocetTipov++;
        }
    }
    public int pocetTipov() {
        return pocetTipov;
    }
    public int kolkoOtazok() {
        return pocetOtazok;
    }
    public boolean uhadolCislo() {
        return uhadol;
    }
    public int kolkoFalse() {
        return pocetFalse;
    }
}

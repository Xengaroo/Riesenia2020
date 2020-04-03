
public class VrutkyStrategia {
    public static void strategia(Vrutky m) {
        int n = m.doKolkoHrame();
        int zac = (int) Math.ceil((-1 + Math.sqrt(1 + 8 * n)) / 2);
        int min = 0;
        for (int i = 0; i < zac; i++) {
            if (m.jeVacsieAko(min + zac - i)) {
                min += zac - i;
            } else {
                int max = min + zac - i;
                while (min + 1 < max && m.jeVacsieAko(min + 1)) {
                    min++;
                }
                break;
            }
        }
        m.jetoCislo(min + 1);
    }

    public static void main(String[] args) {
        strategia(new Vrutky());
    }
}

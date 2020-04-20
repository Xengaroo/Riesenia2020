import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.*;

public class TestOsadnici {

    @Test
    public void testNajviacCiest1() {
        Map<Suradnica, Policko> hraciaPlocha = new HashMap<Suradnica, Policko>();
        hraciaPlocha.put(new Suradnica(-1, 0), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, -1), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, 0), new Dedina("Yoda"));
        hraciaPlocha.put(new Suradnica(1, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(1, 0), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, -1), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, 0), new Cesta("Yoda"));

        hraciaPlocha.put(new Suradnica(2, 2), new Cesta("Darth Vader"));
        hraciaPlocha.put(new Suradnica(3, 2), new Dedina("Darth Vader"));
        hraciaPlocha.put(new Suradnica(3, 3), new Dedina("Darth Vader"));

        /* hraciaPlocha:
         *
         *     -2, -1, 0, 1, 2, 3
         *  -1         C
         *   0  C   C  D
         *   1  C      C
         *   2  C   C  C     C
         *   3               D  D
         */
        Osadnici osadnici = new Osadnici(hraciaPlocha);
        assertEquals("najviac ciest", "Yoda", osadnici.najviacCiest());
    }

    @Test
    public void testNajviacCiest2() {
        Map<Suradnica, Policko> hraciaPlocha = new HashMap<Suradnica, Policko>();
        hraciaPlocha.put(new Suradnica(-100, -100), new Cesta("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(-100, -99), new Cesta("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(-100, -98), new Cesta("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(-100, -97), new Cesta("Luke Skywalker"));

        hraciaPlocha.put(new Suradnica(-99, -100), new Cesta("R2D2"));
        hraciaPlocha.put(new Suradnica(-99, -99), new Cesta("R2D2"));
        hraciaPlocha.put(new Suradnica(-99, -98), new Cesta("R2D2"));
        hraciaPlocha.put(new Suradnica(-99, -97), new Cesta("R2D2"));

        hraciaPlocha.put(new Suradnica(-98, -100), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(-98, -99), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(-98, -98), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(-98, -97), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(-98, -96), new Cesta("C3PO"));

        hraciaPlocha.put(new Suradnica(108, -100), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(108, -99), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(108, -98), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(108, -97), new Cesta("Obi-Wan"));

        Osadnici osadnici = new Osadnici(hraciaPlocha);
        assertEquals("najviac ciest", "C3PO", osadnici.najviacCiest());
    }

    @Test
    public void testNajviacCiest3() {
        Random rnd = new Random();
        Map<Suradnica, Policko> hraciaPlocha = new HashMap<Suradnica, Policko>();

        // vygeneruj nahodne pocty ciest
        String[] hraci = {"Luke Skywalker", "Yoda", "C3PO", "Obi-Wan", "R2D2", "Darth Vader"};
        Map<String, Integer> pocetCiest = new HashMap<>();
        String hracNajviac = "";
        int pocetNajviac = 0;
        for (String hrac : hraci) {
            int ciest = rnd.nextInt(10000);
            if (ciest > pocetNajviac) {
                pocetNajviac = ciest;
                hracNajviac = hrac;
            }
            pocetCiest.put(hrac, ciest);
        }

        // vygeneruj nahodne cesty v hracej ploche
        for (String hrac : hraci) {
            int ciestHraca = pocetCiest.get(hrac);
            int i = 0;
            while (i < ciestHraca) {
                int r = rnd.nextInt(10000) - 5000;
                int s = rnd.nextInt(10000) - 5000;
                Suradnica suradnica = new Suradnica(r, s);

                if (hraciaPlocha.containsKey(suradnica)) continue;

                hraciaPlocha.put(suradnica, new Cesta(hrac));
                i++;
            }
        }

        Osadnici osadnici = new Osadnici(hraciaPlocha);
        assertEquals("najviac ciest", hracNajviac, osadnici.najviacCiest());
    }

    @Test
    public void testCyklickeCesty1() {
        Map<Suradnica, Policko> hraciaPlocha = new HashMap<Suradnica, Policko>();
        hraciaPlocha.put(new Suradnica(-1, 0), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, -1), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(0, 0), new Dedina("Yoda"));
        hraciaPlocha.put(new Suradnica(1, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(1, 0), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, -2), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, -1), new Cesta("Yoda"));
        hraciaPlocha.put(new Suradnica(2, 0), new Cesta("Yoda"));

        hraciaPlocha.put(new Suradnica(2, 2), new Cesta("Darth Vader"));
        hraciaPlocha.put(new Suradnica(3, 2), new Dedina("Darth Vader"));
        hraciaPlocha.put(new Suradnica(3, 3), new Dedina("Darth Vader"));

        /* hraciaPlocha:
         *
         *     -2, -1, 0, 1, 2, 3
         *  -1         C
         *   0  C   C  D
         *   1  C      C
         *   2  C   C  C     C
         *   3               D  D
         */
        Osadnici osadnici = new Osadnici(hraciaPlocha);
        assertEquals("cyklicke cesty", true, osadnici.cyklickeCesty("Yoda"));
        assertEquals("cyklicke cesty", false, osadnici.cyklickeCesty("Darth Vader"));
    }

    @Test
    public void testCyklickeCesty2() {
        Map<Suradnica, Policko> hraciaPlocha = new HashMap<Suradnica, Policko>();
        hraciaPlocha.put(new Suradnica(0, 0), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(0, 1), new Dedina("C3PO"));
        hraciaPlocha.put(new Suradnica(0, 2), new Cesta("C3PO"));
        hraciaPlocha.put(new Suradnica(1, 0), new Dedina("C3PO"));
        hraciaPlocha.put(new Suradnica(1, 2), new Cesta("C3PO"));

        hraciaPlocha.put(new Suradnica(2, 0), new Cesta("R2D2"));
        hraciaPlocha.put(new Suradnica(2, 1), new Cesta("R2D2"));
        hraciaPlocha.put(new Suradnica(2, 2), new Cesta("R2D2"));

        hraciaPlocha.put(new Suradnica(0, 4), new Dedina("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(0, 5), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(0, 6), new Dedina("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(1, 4), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(1, 6), new Dedina("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(2, 4), new Cesta("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(2, 5), new Dedina("Obi-Wan"));
        hraciaPlocha.put(new Suradnica(2, 6), new Cesta("Obi-Wan"));

        hraciaPlocha.put(new Suradnica(4, 0), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(5, 0), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(6, 0), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(4, 2), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(5, 2), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(6, 0), new Dedina("Luke Skywalker"));
        hraciaPlocha.put(new Suradnica(6, 1), new Dedina("Luke Skywalker"));

        Osadnici osadnici = new Osadnici(hraciaPlocha);
        assertEquals("cyklicke cesty", false, osadnici.cyklickeCesty("C3PO"));
        assertEquals("cyklicke cesty", false, osadnici.cyklickeCesty("R2D2"));
        assertEquals("cyklicke cesty", true, osadnici.cyklickeCesty("Obi-Wan"));
        assertEquals("cyklicke cesty", false, osadnici.cyklickeCesty("Luke Skywalker"));
    }
}
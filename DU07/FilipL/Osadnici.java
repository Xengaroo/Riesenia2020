import java.util.*;
import java.util.stream.Collectors;

public class Osadnici {
        Map<Suradnica, Policko> hraciaPlocha;

    public Osadnici(Map<Suradnica, Policko> hraciaPlocha) {
        this.hraciaPlocha = hraciaPlocha;
    }

    public String najviacCiest() {
        //TATO ULOHA JE
        //................................................................................................................................
        //.................FFFFFFFFF...OOOOOOO.....RRRRRRRRRR........FFFFFFFFFF.RRRRRRRRRR...EEEEEEEEEEE.EEEEEEEEEEE......................
        //.................FFFFFFFFF..OOOOOOOOOO...RRRRRRRRRRR.......FFFFFFFFFF.RRRRRRRRRRR..EEEEEEEEEEE.EEEEEEEEEEE......................
        //.................FFFFFFFFF.OOOOOOOOOOOO..RRRRRRRRRRR.......FFFFFFFFFF.RRRRRRRRRRR..EEEEEEEEEEE.EEEEEEEEEEE......................
        //.................FFF.......OOOOO..OOOOO..RRRR...RRRRR......FFFF.......RRRR...RRRRR.EEEE........EEEE.............................
        //.................FFF......FOOOO....OOOOO.RRRR...RRRRR......FFFF.......RRRR...RRRRR.EEEE........EEEE.............................
        //.................FFFFFFFF.FOOO......OOOO.RRRRRRRRRRR.......FFFFFFFFF..RRRRRRRRRRR..EEEEEEEEEE..EEEEEEEEEE.......................
        //.................FFFFFFFF.FOOO......OOOO.RRRRRRRRRRR.......FFFFFFFFF..RRRRRRRRRRR..EEEEEEEEEE..EEEEEEEEEE.......................
        //.................FFFFFFFF.FOOO......OOOO.RRRRRRRR..........FFFFFFFFF..RRRRRRRR.....EEEEEEEEEE..EEEEEEEEEE.......................
        //.................FFF......FOOOO....OOOOO.RRRR.RRRR.........FFFF.......RRRR.RRRR....EEEE........EEEE.............................
        //.................FFF.......OOOOO..OOOOO..RRRR..RRRR........FFFF.......RRRR..RRRR...EEEE........EEEE.............................
        //.................FFF.......OOOOOOOOOOOO..RRRR..RRRRR.......FFFF.......RRRR..RRRRR..EEEEEEEEEEE.EEEEEEEEEEE.E....................
        //.................FFF........OOOOOOOOOO...RRRR...RRRRR......FFFF.......RRRR...RRRRR.EEEEEEEEEEE.EEEEEEEEEEE.E....................
        //.................FFF..........OOOOOO.....RRRR....RRRR......FFFF.......RRRR....RRRR.EEEEEEEEEEE.EEEEEEEEEEE.E....................
        //................................................................................................................................
        Map<String, List<Policko>> mapa = this.hraciaPlocha.values().stream().filter(Objects::nonNull).collect(Collectors.groupingBy(policko -> policko.vlastnik));
        return mapa.keySet().stream().max(Comparator.comparingInt(key -> mapa.get(key).size())).get();
    }

    public boolean cyklickeCesty(String hrac) {
        Map<Suradnica, Policko> polickaHraca = new HashMap<>();
        for (Suradnica i : hraciaPlocha.keySet()) {
            if (hraciaPlocha.get(i).vlastnik.equals(hrac)) {
                polickaHraca.put(i, hraciaPlocha.get(i));
            }
        }


        for (Policko i : polickaHraca.values()) {
            List<Policko> visited = new ArrayList<>();
            visited.add(i);
            if (cyklusRek(1,null, i, polickaHraca, visited)) { //prechadza vsetky policka, ak aspon raz najde cyklus vrati true
                return true;
            }
        }
        return false;

    }

    public boolean cyklusRek(int dlzkaCesty, Policko aktualne, Policko hladane, Map<Suradnica, Policko> polickaHraca, List<Policko> visited) {
        Policko iter;
        if (aktualne == null) { //ak je to prvy rekurzivny prechod, nemame este aktualne policko, preto zvolime nahodneho suseda
            Suradnica iterSur = polickaHraca.keySet().stream().filter(suradnica -> polickaHraca.get(suradnica).equals(hladane)).findFirst().get();
            iter = polickaHraca.get(polickaHraca.keySet().stream().filter(suradnica -> jeSused(suradnica, iterSur)).findAny().get());
        } else iter = aktualne;

        //suradnica policka, ktore aktualne prechadzame
        Suradnica iterSur = polickaHraca.keySet().stream().filter(suradnica -> polickaHraca.get(suradnica).equals(iter)).findFirst().get();


        visited.add(iter);
        List<Policko> susedia = new ArrayList<>();

        for (Suradnica i : polickaHraca.keySet()) {
            if (jeSused(i, iterSur)) susedia.add(polickaHraca.get(i));
        }

        for (Policko sused : susedia) {
            if (dlzkaCesty > 2 && sused.equals(hladane)) return true; //ak sa sused rovna hladanemu policku, musi to byt cyklus
            else if (!visited.contains(sused)) return cyklusRek(dlzkaCesty + 1, sused, hladane, polickaHraca, visited); //ak nie, rekurzivne hladame dalsieho
        }
        return false;
    }

    public boolean jeSused(Suradnica s1, Suradnica s2) {
        if (Math.abs(s1.r - s2.r) <= 1 && s1.s == s2.s) {
            return true;
        }


        if (Math.abs(s1.s - s2.s) <= 1 && s1.r == s2.r) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
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
        System.out.println(osadnici.najviacCiest()); // Yoda
        System.out.println(osadnici.cyklickeCesty("Yoda")); // true
        System.out.println(osadnici.cyklickeCesty("Darth Vader")); // false
    }
}

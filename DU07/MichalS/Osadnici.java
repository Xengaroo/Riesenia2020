import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Osadnici {
        Map<Suradnica, Policko> hraciaPlocha;

    public Osadnici(Map<Suradnica, Policko> hraciaPlocha) {
        this.hraciaPlocha = hraciaPlocha;
    }

    /************************
     *   --- FOR FREE ---   *
     ************************/
    public String najviacCiest() {
        if (hraciaPlocha == null) return null;
        Optional<String> o = zoznamHracov().max(Comparator.comparing(this::pocetCiestHraca));
        if (o.isEmpty()) return null;
        return o.get();
    }

    private long pocetCiestHraca(String hrac) {
        return hraciaPlocha.values().stream().filter(p -> p instanceof Cesta).map(p -> p.vlastnik).filter(hrac::equals).count();
    }

    private Stream<String> zoznamHracov() {
        return hraciaPlocha.values().stream().map(policko -> policko.vlastnik).distinct();
    }

    // je to bez for cyklov, ale s rekurziou...
    public boolean cyklickeCesty(String hrac) {
        if (hraciaPlocha == null || hrac == null) return false;
        return hraciaPlocha.keySet().stream().filter(s -> hraciaPlocha.get(s).vlastnik.equals(hrac))
                .anyMatch(s -> susedne(s, hrac).anyMatch(ss -> existujeCesta(ss, s, Set.of(s, ss), hrac)));
    }

    private boolean existujeCesta(Suradnica start, Suradnica ciel, Set<Suradnica> navstivene, String hrac) {
        Set<Suradnica> sus = susedne(start, hrac).collect(Collectors.toSet());
        if (sus.contains(ciel) && navstivene.size() > 2) {
            return true;
        }
        sus.removeAll(navstivene);
        return sus.stream().anyMatch(x -> existujeCesta(x, ciel, pridaj(navstivene, x), hrac));
    }

    private Stream<Suradnica> susedne(Suradnica s, String hrac) {
        Predicate<Suradnica> jeHraca = i -> hraciaPlocha.get(i).vlastnik.equals(hrac);
        Predicate<Suradnica> jeSused = i -> Math.abs(i.r - s.r) + Math.abs(i.s - s.s) == 1;
        return hraciaPlocha.keySet().stream().filter(jeHraca).filter(jeSused);
    }

    private Set<Suradnica> pridaj(Set<Suradnica> mnozina, Suradnica prvok) {
        Set<Suradnica> vysl = new HashSet<>(mnozina);
        vysl.add(prvok);
        return vysl;
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

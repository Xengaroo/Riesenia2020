import java.util.*;

public class Osadnici {
        Map<Suradnica, Policko> hraciaPlocha;

    public Osadnici(Map<Suradnica, Policko> hraciaPlocha) {
        this.hraciaPlocha = hraciaPlocha;
    }

    private Set<String> hraci(){
        Set<String> res = new HashSet<>();
        hraciaPlocha.values().stream().filter(Objects::nonNull).filter(policko -> policko.vlastnik != null).forEach(policko -> res.add(policko.vlastnik));
        return res;
    }
    private Set<Policko> hracove_cesty(String hrac){
        Set<Policko> res = new HashSet<>();
        if(hrac == null) return res;
        hraciaPlocha.values().stream().filter(Objects::nonNull).filter(p -> hrac.equals(p.vlastnik)).filter(p->p.getClass() == Cesta.class).forEach(res::add);
        return res;
    }
    private  Set<Suradnica> hracova_mapa(String hrac){
        Set<Suradnica> res = new HashSet<>();
        if(hrac == null) return res;
        hraciaPlocha.keySet().stream().filter(kluc -> hraciaPlocha.get(kluc) != null).filter(kluc->hrac.equals(hraciaPlocha.get(kluc).vlastnik)).forEach(res::add);
        return res;
    }

    public String najviacCiest() {
        String max = null;
        Set<String> hraci = hraci();
        Map<Integer, String> poc = new HashMap<>();
        hraci.stream().forEach(hrac -> poc.put(hracove_cesty(hrac).size(), hrac));
        if(poc.keySet().stream().max(Integer::compareTo).isPresent()){
            max = poc.get(poc.keySet().stream().max(Integer::compareTo).get());
        }
        return max;
    }

    public boolean cyklickeCesty(String hrac) {
        Set<Suradnica> m = hracova_mapa(hrac);
        return m.stream().anyMatch(kluc -> cyklickeCestyRek(hrac, kluc, kluc, Set.of(kluc)));

    }
    private boolean cyklickeCestyRek(String hrac, Suradnica zac, Suradnica akt, Set<Suradnica> visited){
        if(akt.equals(zac) && visited.size() >= 3){
            return true;
        }
        Set<Suradnica> m = hracova_mapa(hrac);
        List<Suradnica> sus = susedi(akt);
        return sus.stream().filter(m::contains).filter(p -> !visited.contains(p) || (p.equals(zac)&& visited.size() >= 3)).anyMatch(p-> {
            Set<Suradnica> pom = new HashSet<>(visited);
            pom.add(p);
            return cyklickeCestyRek(hrac, zac, p, pom);
        });
    }

    private boolean susedi(Suradnica s1, Suradnica s2){
        if((s1.r == s2.r - 1 || s1.r == s2.r + 1) && s1.s == s2.s) return true;
        return (s1.s == s2.s - 1 || s1.s == s2.s + 1) && s1.r == s2.r;
    }
    private List<Suradnica> susedi(Suradnica s1){
        List<Suradnica> res = new LinkedList<>();
        hraciaPlocha.keySet().stream().filter(p->susedi(s1,p)).forEach(res::add);
        return res;
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

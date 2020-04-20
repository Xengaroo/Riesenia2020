import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

	Comparator<Ziak> comparing = new Comparator<Ziak>() {
        @Override
        public int compare(Ziak ziak, Ziak t1) {
            if (trieda.get(ziak) == null) {
                if (trieda.get(t1) == null) {
                    return 0;
                }
                return -1;
            }
            if (trieda.get(t1) == null) {
                return 1;
            }
            return Integer.compare(trieda.get(ziak).size(), trieda.get(t1).size()) == 0 ? Integer.compare(ziak.getAge(), t1.getAge()) : Integer.compare(trieda.get(ziak).size(), trieda.get(t1).size());
        }
    };

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
                //dost viditelne? :D

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
      return trieda.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).distinct().collect(Collectors.toList()); // doprogramuj
    }

    public List<Ziak> bezVajec() {
      return trieda.keySet().stream().filter(ziak -> trieda.get(ziak) == null || trieda.get(ziak).size() == 0).collect(Collectors.toList()); // doprogramuj
    }

    public Ziak najvacsiZberatel() {
      return trieda.keySet().stream().filter(Objects::nonNull).max(comparing).get(); // doprogramuj
    }

    public VelkonocneVajce najcastejsieVajce() {
        Map<Integer, List<VelkonocneVajce>> vajcia =  trieda.values().stream().distinct().filter(Objects::nonNull).flatMap(Collection::stream)
                .collect(Collectors.groupingBy(velkonocneVajce -> (int) trieda.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).filter(v -> v.equals(velkonocneVajce)).count()));
    //rozdeli vajcia podla poctu

      return  vajcia.get(vajcia.keySet().stream().max(Integer::compareTo).get()).stream().min(VelkonocneVajce::compareTo).get(); // vyberie z najvacsieho poctu maximum podla comparatora
    }	  
    public VelkonocneVajce najvacsieVajce() {
      return trieda.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).filter(Objects::nonNull).max(VelkonocneVajce::compareTo).get(); // doprogramuj
    }

	//------------------------------------------------------------------------------
	public Trieda(HashMap<Ziak, List<VelkonocneVajce>> trieda) {
		super();
		this.trieda = trieda;
	}

	public HashMap<Ziak, List<VelkonocneVajce>> getTrieda() {
		return trieda;
	}

	@Override
	public String toString() {
		return "Trieda [trieda=" + trieda + "]";
	}
}

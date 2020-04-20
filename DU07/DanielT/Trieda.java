import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//******************************************
//******          FOR FREE            ******
//******************************************
public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
        Set<VelkonocneVajce> res = new HashSet<>();
        trieda.values().stream().filter(Objects::nonNull).forEach(vajicia -> vajicia.stream().filter(Objects::nonNull).forEach(res::add));
        return new LinkedList<>(res);
    }    
    public List<Ziak> bezVajec() {
        Set<Ziak> res = new HashSet<>();
        trieda.keySet().stream().filter(ziak -> trieda.get(ziak) == null || trieda.get(ziak).size() == 0).forEach(res::add);
        return new LinkedList<>(res);
    }
    public Ziak najvacsiZberatel() {
        Comparator<Ziak> comp = new Comparator<Ziak>() {
            @Override
            public int compare(Ziak ziak, Ziak t1) {
                if(trieda.get(ziak) == null && trieda.get(ziak) == null){
                    return Integer.compare(ziak.getAge(), t1.getAge());
                }
                if(trieda.get(ziak) == null){
                    return 1;
                }
                if(trieda.get(t1) == null){
                    return -1;
                }
                if(trieda.get(ziak).size() - trieda.get(t1).size() == 0){
                    return Integer.compare(ziak.getAge(), t1.getAge());
                }
                return Integer.compare(trieda.get(ziak).size(), trieda.get(t1).size());
            }
        };
        return trieda.keySet().stream().max(comp).get();
    }
    private List<VelkonocneVajce> vsetkyVajcia() {
        List<VelkonocneVajce> res = new LinkedList<>();
        trieda.values().stream().filter(Objects::nonNull).forEach(vajicia -> vajicia.stream().filter(Objects::nonNull).forEach(res::add));

        return res;
    }
    public VelkonocneVajce najcastejsieVajce() {
        final VelkonocneVajce[] max = new VelkonocneVajce[1];
        final int[] freq = {0};
        List<VelkonocneVajce> allDifferent = vsetkyRozneVajcia();
        List<VelkonocneVajce> all = vsetkyVajcia();
        allDifferent.forEach(vajce -> {
            int f = Collections.frequency(all, vajce);
            if(f > freq[0]){
                freq[0] = f;
                max[0] = vajce;
            }
            else if(f == freq[0]){
                if(vajce.compareTo(max[0]) < 0){
                    max[0] = vajce;
                }
            }
        });
        return max[0];
    }	  
    public VelkonocneVajce najvacsieVajce() {
        Comparator<VelkonocneVajce> comp = new Comparator<VelkonocneVajce>() {
            @Override
            public int compare(VelkonocneVajce velkonocneVajce, VelkonocneVajce t1) {
                return velkonocneVajce.compareTo(t1);
            }
        };
        List<VelkonocneVajce> vsetky = vsetkyRozneVajcia();
        return vsetky.stream().max(comp).get();
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

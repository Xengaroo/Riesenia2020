import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

//============================\\
//                            \\
//          FOR FREE          \\
//                            \\
//============================\\

public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

    public List<VelkonocneVajce> vsetkyRozneVajcia() {
        return trieda.values().stream().filter(Objects::nonNull).flatMap(Collection::stream).distinct().collect(Collectors.toList());
    }
    public List<Ziak> bezVajec() {
      return trieda.entrySet().stream().filter(x -> x.getValue() == null || x.getValue().size() == 0).map(x -> x.getKey()).collect(Collectors.toList());
    }

    private int compareByAmountThenName(Map.Entry<Ziak, List<VelkonocneVajce>> z, Map.Entry<Ziak, List<VelkonocneVajce>> z2) {
        if (z.getValue().size() == z2.getValue().size()) return Ziak.compareByNameThenAge(z2.getKey(), z.getKey());
        return Integer.compare(z2.getValue().size(), z.getValue().size());
    }

    public Ziak najvacsiZberatel() {
        return trieda.entrySet().stream().filter(v -> v.getValue() != null && v.getValue().size() != 0).sorted((x, y) -> compareByAmountThenName(x, y)).map(ziak -> ziak.getKey()).collect(Collectors.toList()).get(0);
    }
    public VelkonocneVajce najcastejsieVajce() {
        return Collections.max(trieda.values().stream().filter(z -> z != null && z.size() > 0).flatMap(v -> v.stream()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet(), (v1, v2) -> {
            return v1.getValue().equals(v2.getValue()) ? Integer.compare(v2.getKey().getSize(), v1.getKey().getSize()) : Long.compare(v1.getValue(), v2.getValue());
        }).getKey();
    }

    public VelkonocneVajce najvacsieVajce() {
        return (vsetkyRozneVajcia().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList())).get(0);
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

    public static void main(String[] args) {

    }
}

//[trieda=
//        {Ziak[name=Ziak1,age=8]=[VV{c=ff00ffff,s=14},VV{c=ffffff00,s=2},VV{c=ff00ffff,s=0},VV{c=ff0000ff,s=16},VV{c=ff00ffff,s=2}],
//        Ziak[name=Ziak2,age=9]=[VV{c=ff000000,s=7},VV{c=ff0000ff,s=9},VV{c=ffff0000,s=5},VV{c=ff00ffff,s=10},VV{c=ff000000,s=13}],
//        Ziak[name=Ziak3,age=11]=[VV{c=ff00ff00,s=16},VV{c=ff000000,s=16},VV{c=ffff00ff,s=17},VV{c=ffffffff,s=1}],
//        Ziak[name=Ziak0,age=6]=[VV{c=ff000000,s=3},VV{c=ffff00ff,s=7},VV{c=ffff00ff,s=12},VV{c=ff000000,s=12}],
//        Ziak[name=Ziak6,age=18]=[VV{c=ff0000ff,s=15},VV{c=ff00ffff,s=1},VV{c=ffffffff,s=3},VV{c=ff00ff00,s=14}],
//        Ziak[name=Ziak4,age=14]=[VV{c=ff00ffff,s=13},VV{c=ff00ffff,s=16},VV{c=ffff0000,s=0}],
//        Ziak[name=Ziak5,age=15]=[VV{c=ff00ffff,s=18},VV{c=ff0000ff,s=6},VV{c=ff000000,s=12},VV{c=ff0000ff,s=8},VV{c=ffffffff,s=0}]
//        ]

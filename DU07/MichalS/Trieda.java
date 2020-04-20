import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Trieda {
	private final HashMap<Ziak, List<VelkonocneVajce>> trieda;

	/************************
     *   --- FOR FREE ---   *
     ************************/
    public List<VelkonocneVajce> vsetkyRozneVajcia() {
        if (trieda == null) return null;
        return vsetkyVajcia().distinct().collect(Collectors.toList());
    }

    private Stream<VelkonocneVajce> vsetkyVajcia() {
        return trieda.values().stream().flatMap(e -> { if (e == null) return Stream.empty(); return e.stream(); });
    }

    /************************
     *   --- FOR FREE ---   *
     ************************/
    public List<Ziak> bezVajec() {
        if (trieda == null) return null;
        Predicate<Ziak> nemaVajce = ziak -> (trieda.get(ziak) == null || trieda.get(ziak).size() == 0);
        return trieda.keySet().stream().filter(nemaVajce).collect(Collectors.toList());
    }

    /************************
     *   --- FOR FREE ---   *
     ************************/
    public Ziak najvacsiZberatel() {
        if (trieda == null) return null;
        Optional<Ziak> o = trieda.keySet().stream().max(Comparator
                .comparing((Ziak a) -> {if (trieda.get(a) == null) return 0; return trieda.get(a).size();})
                .thenComparing(Ziak::getAge));
        if (o.isEmpty())  return null;
        return o.get();
    }

    /************************
     *   --- FOR FREE ---   *
     ************************/
    public VelkonocneVajce najvacsieVajce() {
        if (trieda == null) return null;
        Optional<VelkonocneVajce> o = vsetkyRozneVajcia().stream().max(VelkonocneVajce::compareTo);
        if (o.isEmpty()) return null;
        return o.get();
    }

    /************************
     *   --- FOR FREE ---   *
     ************************/
    public VelkonocneVajce najcastejsieVajce() {
        if (trieda == null) return null;
        Optional<VelkonocneVajce> o = vsetkyRozneVajcia().stream().max(Comparator
                .comparing(this::pocetVajecDruhu)
                .thenComparing((VelkonocneVajce v) -> 20 - v.getSize()));
        if (o.isEmpty()) return null;
        return o.get();
    }

    private long pocetVajecDruhu(VelkonocneVajce v) {
        return vsetkyVajcia().filter(i -> i.equals(v)).count();
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

public class FibonaccihoPostupnost extends Postupnost {
	protected long nulty;
	protected long predch;

	FibonaccihoPostupnost(long _nulty, long _prvy) {
		predch = nulty = _nulty;
		aktualny = prvy = _prvy;
	}
	
	public long prvy() {
		predch = nulty;
		aktualny = prvy;
		return prvy;
	}

	@Override
	public void reset() {
		predch = prvy - nulty;
		aktualny = nulty;
	}

	public long dalsi() {
		long pom = aktualny;
		aktualny += predch;
		predch = pom;
		return aktualny;
	}

	@Override
	public long dalsiParny() {
		long ret = dalsi();
		while (ret % 2 != 0){
			ret = dalsi();
		}
		return ret;
	}
}

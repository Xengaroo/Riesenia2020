public class Kruh extends Utvar {
	private Bod stred;
	private double polomer;

	public Kruh(Bod stred, double r) {
		super();
		this.stred = stred;
		this.polomer = r;
	}

	@Override
	double obsah() {
		return Math.PI * Math.pow(polomer, 2);
	}

	@Override
	double obvod() {
		return 2 * Math.PI * polomer;
	}

	@Override
	boolean obsahuje(Bod p) {
		double epsilon = 0.001;
		double dist = Math.abs(vzdialenost(stred, p));
		if (Math.abs(dist - polomer) < epsilon) {
			return true;
		}
		if (dist < polomer) {
			return true;
		}
		return false;
	}
}

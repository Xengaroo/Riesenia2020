public class Kruh extends Utvar {
	Bod stred;
	double r;
	public Kruh(Bod stred, double r) {
		this.stred = stred;
		this.r = r;
	}

	double distance_2points(double x1, double x2, double y1, double y2) {
		return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
	}

	@Override
	double obsah() {
		return Math.PI*r*r;
	}

	@Override
	double obvod() {
		return 2*Math.PI*r;
	}

	@Override
	boolean obsahuje(Bod p) {
		return distance_2points(p.getX(), stred.getX(), p.getY(), stred.getY()) <= r*r;
		//return Math.pow(p.getX()-stred.getX(), 2) + Math.pow(p.getY() - stred.getY(), 2) <= r*r;
	}
}

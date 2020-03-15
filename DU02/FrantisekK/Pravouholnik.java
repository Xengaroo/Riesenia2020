public class Pravouholnik extends Utvar {

	Bod lh;
	double sx, sy;

	public Pravouholnik(Bod lh, double sx, double sy) {
		super();
		this.lh = lh;
		this.sx = sx;
		this.sy = sy;
	}

	double distance_2points(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	@Override
	double obsah() {
		return sx*sy;
	}

	@Override
	double obvod() {
		// doprogramuj
		return sx*2 + sy*2;
	}

	@Override
	boolean obsahuje(Bod p) {
		return  lh.getX() <= p.getX() && p.getX() <= (lh.getX() + sx) &&
				lh.getY() <= p.getY() && p.getY() <= (lh.getY() + sy);
	}
}

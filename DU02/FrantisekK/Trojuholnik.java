public class Trojuholnik extends Utvar {

	Bod a, b, c;
	double x,y,z;  // dlzky stran

	public Trojuholnik(Bod a, Bod b, Bod c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		x = distance_2points(a.getX(), b.getX(), a.getY(), b.getY());
		y = distance_2points(b.getX(), c.getX(), b.getY(), c.getY());
		z = distance_2points(a.getX(), c.getX(), a.getY(), c.getY());
	}

	double distance_2points(double x1, double x2, double y1, double y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}

	double distance_2pointsInTypeBod(Bod x1, Bod x2) {
		return distance_2points(x1.getX(), x2.getX(), x1.getY(), x2.getY());
	}

	@Override
	double obsah() {	// H-formula
		double s = obvod()/2;
		return Math.sqrt(s*(s-x)*(s-y)*(s-z));
	}

	double obsah_general(Bod x1, Bod x2, Bod P) {
		double s = obvod_general(x1, x2, P)/2;
		double side1 = distance_2pointsInTypeBod(x1, x2);
		double side2 = distance_2pointsInTypeBod(x2, P);
		double side3 = distance_2pointsInTypeBod(x1, P);
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}

	@Override
	double obvod() {
		return  x + y + z;
	}

	double obvod_general(Bod x1, Bod y1, Bod P) {
		double side1 = distance_2points(x1.getX(), y1.getX(), x1.getY(), y1.getY());
		double side2 = distance_2points(y1.getX(), P.getX(), y1.getY(), P.getY());
		double side3 = distance_2points(x1.getX(), P.getX(), x1.getY(), P.getY());
		return  side1 + side2 + side3;
	}

	@Override
	boolean obsahuje(Bod p) {
		double obsah_malych_troj = obsah_general(a,b,p) + obsah_general(a,c,p) + obsah_general(b,c, p);
		return Math.abs(obsah() - obsah_malych_troj) < 0.001;
    }
}

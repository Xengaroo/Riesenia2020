public class Trojuholnik extends Utvar {
	private Bod a,b,c;
	private double stranaA, stranaB, stranaC;

	public Trojuholnik(Bod a, Bod b, Bod c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;

		this.stranaA = Math.abs(vzdialenost(c, b));
		this.stranaB = Math.abs(vzdialenost(a, c));
		this.stranaC = Math.abs(vzdialenost(a, b));


	}

	@Override
	double obsah() {	// H-formula
		double s = this.obvod()/2;

		return Math.sqrt(s * (s - stranaA) * (s - stranaB) * (s - stranaC));
	}

	@Override
	double obvod() {
		return stranaA + stranaB + stranaC;
	}

	@Override
	boolean obsahuje(Bod p) {
		double epsilon = 0.001;
		double o = obsah();

		double stranaAP = Math.abs(vzdialenost(a, p));
		double stranaBP = Math.abs(vzdialenost(b, p));
		double stranaCP = Math.abs(vzdialenost(c, p));

		double s_ABP = (stranaC + stranaAP + stranaBP)/2;
		double s_BPC = (stranaA + stranaBP + stranaCP)/2;
		double s_APC = (stranaB + stranaAP + stranaCP)/2;

		double o_ABP = Math.sqrt(s_ABP * (s_ABP - stranaAP) * (s_ABP - stranaBP) * (s_ABP - stranaC));
		double o_BPC = Math.sqrt(s_BPC * (s_BPC - stranaBP) * (s_BPC - stranaCP) * (s_BPC - stranaA));
		double o_APC = Math.sqrt(s_APC * (s_APC - stranaAP) * (s_APC - stranaCP) * (s_APC - stranaB));

		double obsahy = o_ABP + o_BPC + o_APC;

		if (Math.abs(o - obsahy) < epsilon) {
			return true;
		}
		return false;
    }
}

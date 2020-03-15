import java.lang.Math;

abstract class Utvar {
	abstract double obsah();
	abstract double obvod();
	abstract boolean obsahuje(Bod p);
	double vzdialenost(Bod a, Bod b) {
		return Math.sqrt(Math.pow((a.getX() - b.getX()), 2) + Math.pow((a.getY() - b.getY()), 2));
	};
}

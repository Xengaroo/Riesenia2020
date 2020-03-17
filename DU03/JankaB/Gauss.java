import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Gauss {
	private double[][] p;	// vnutorna reprezentacia musi zostat private
	public Gauss(double[][] pole) {
		this.p = pole.clone();
		for(int i = 0; i < pole.length; i++)
			this.p[i] = pole[i].clone();
	}
	@Override
	public String toString() {
		StringBuffer res = new StringBuffer("{\n");
		for(int i = 0; i < p.length; i++) {
			res.append("\t{");
			for(int j = 0; j < p[i].length; j++) {
				res.append(p[i][j] + ((j+1 <p[i].length)?", ":""));
			}
			res.append((i+1 < p.length)?"},\n":"} \n}\n"); 
		}
		return res.toString(); 
	}
	public double[] dajRiadok(int r) {
		return p[r];
	}
	//----------------------------------- nad ciarou nemodifikujte, programujte pod ciarou
	/*
	 * vynasobi riadok pola cim
	 */
	public void vydel(int riadok, double cim) {
		if (p[riadok] == null) return;
		for (int i = 0; i < p[riadok].length; i++) {
			p[riadok][i] /= cim;
		}
	}
	/*
	 * vymeni riadok1 a riadok2
	 */
	public void vymen(int riadok1, int riadok2) {
		double[] pomocny_r = new double[p[riadok1].length];
		pomocny_r = p[riadok1].clone();
		p[riadok1] = p[riadok2].clone();
		p[riadok2] = pomocny_r.clone();
	}
	/*
	 * k riadku riadok1 pripocitaj x-nasobok riadku riadok2
	 */
	public void pripocitaj(int riadok1, double x, int riadok2) {
		if (p[riadok1] == null) return;
		if (p[riadok2] == null) return;
		for (int i = 0; i < p[riadok1].length; i++) {
			p[riadok1][i] += p[riadok2][i] * x;
		}
	}	
	/*
	 * Gaussova eliminacna metoda pre p = Pole2D(double[M][N]...) - M riadkov N stlpcov
	 * pre kazde r z intervalu [0..M-1]
	 * 	ak je p[r][r] == 0, najdi nejaky riadok j z intervalu [r+1..M-1], ze v r-tom stlpci je p[j][r] != 0
	 *    ak taky riadok j existuje, vymen riadky r a j,
	 *    ak taky riadok j neexistuje, vysledkom je false a skonci (znamena to tazky pripad)
	 * 	teraz urcite uz je p[r][r] != 0
	 * 	vydel riadok r cislom p[r][r]
	 * 	ku kazdemu riadku j != r pripocitaj -p[j][r] nasobok r-teho riadku
	 * ked skoncis cyklus, vrat true, eliminacia sa podarila  
	 */
	public boolean gauss() {
		for (int r = 0; r < p.length; r++) {
			if (p[r][r] == 0) {
				if (r == p.length -1) return false; //posledny riadok sa nema s kym vymenit
				for (int r2 = r+1; r2 < p.length; r2++) {
					if (p[r2][r] != 0) {
						vymen(r2,r);
						break;
					}
					if (r2 == p.length -1) return false; //ak nenasiel s kym sa vymenit
				}
			}
			vydel(r,p[r][r]);

			for (int j = 0; j < p.length; j++) {
				if (j == r) continue;
				pripocitaj(j,-p[j][r],r);
			}
		}
		return true;
	}

	private static boolean equals(double[] dajRiadok, double[] doubles) {
		return Arrays.equals(dajRiadok, doubles);
	}

	public static void main(String[] args) {
		Gauss p = new Gauss(new double[][]{
				{0, 0, 1, 6},
				{0, 1, 0, -3},
				{1, 0, 0, 2}
		});
		assertTrue("test 4:\n" + p, p.gauss());
		assertTrue("test 3:\n" + p, equals(p.dajRiadok(0), new double[] {1.0, 0.0, 0.0, 2}));
		assertTrue("test 3a:\n" + p, equals(p.dajRiadok(1), new double[]{0.0, 1.0, 0.0, -3}));
		assertTrue("test 3b:\n" + p, equals(p.dajRiadok(2), new double[]{0.0, 0.0, 1.0, 6}));
	}
}

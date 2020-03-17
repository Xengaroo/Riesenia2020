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

		for (int i=0; i <= p[riadok].length-1;i++){
			p[riadok][i] = p[riadok][i] / cim;
		}

		return ; // doprogramuj !
	}
	/*
	 * vymeni riadok1 a riadok2
	 */
	public void vymen(int riadok1, int riadok2) {
		double [] pomocnepole = new double[p[riadok1].length];

		for(int c1 = 0; c1 < p[0].length; c1++)
			pomocnepole[c1] = p[riadok1][c1];

		for(int c1 = 0; c1 < p[0].length; c1++)
		{
			p[riadok1][c1] = p[riadok2][c1];
			p[riadok2][c1] = pomocnepole[c1];
		}
	}
	/*
	 * {
		 double[][] pomocnepole;
		pomocnepole = p.clone();

		for (int i=0; i <= p[riadok2].length-1;i++){
			p[riadok1][i] = p[riadok2][i];
		}
		for (int i=0; i <= pomocnepole[riadok1].length-1;i++){
			p[riadok2][i] = pomocnepole[riadok1][i];
		}

		return ; // doprogramuj !
		* chcel som to takto ale nefungovalo to tak som sa inspiroval https://stackoverflow.com/questions/19940740/gaussian-elimination-java
		* ak to nie je ok kontaktujte ma, dakujem
	}
	 */
	public void pripocitaj(int riadok1, double x, int riadok2) {

		for (int i=0; i <= p[riadok1].length-1;i++){
			p[riadok1][i] += p[riadok2][i] * x ;
		}

		return ; // doprogramuj !
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
		int existuje = 0;
		int M = p.length;
		for (int r=0; r <= M-1;r++){
			if(p[r][r] == 0){        // ak je p[r][r] == 0
				existuje = 0;  // ak existuje
				for(int j = r+1;j <= M-1;j++){  // najdi nejaky riadok j z intervalu [r+1..M-1],
					if (p[j][r] != 0){   // ze v r-tom stlpci je p[j][r] != 0
						existuje++; //ak existuje
						vymen(r,j);  // vymen riadky r a j,
					}
				}
				if (existuje ==0) {   // ak taky riadok j neexistuje
					return false;  // vysledkom je false a skonci (znamena to tazky pripad)
					}
				}
			vydel(r,p[r][r]); // vydel riadok r cislom p[r][r]
			for(int i = 0; i<= M-1; i++){      // i = j
				if (r != i){  // ku kazdemu riadku j != r
					pripocitaj(i,-p[i][r],r); // pripocitaj -p[j][r] nasobok r-teho riadku
				}
			}
		}


		return true; // doprogramuj !
	}
	public static void main(String[] args) {
		Gauss p = new Gauss(new double[][]{
			{2, 4, -2, 6},
			{0, -3, 6, -3},
			{4, 1, -2, 2} });
		System.out.println(p.gauss());
		System.out.println(p);
	}
}

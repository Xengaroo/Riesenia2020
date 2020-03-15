public abstract class Vyraz {
	
	public abstract double eval(double[] interpretacia);
	
	public abstract String toString();

	/**
	 * Priklad: Vypocita pravdepodobnost ze budem meskat. Meskam ked mi nezvoni
	 * budik alebo je zapcha.
	 * @param budikZvoni pravdepodobnost ze budik zvoni ako ma
	 * @param zapcha pravdepodobnost ze je zapcha
	 * @return pravdepodobnost ze budem meskat
	 */
	public static double budemMeskat(double budikZvoni, double zapcha) {
		double[] interpretacia = new double[26];
		interpretacia['B' -'A'] = budikZvoni;  // premenna 'B'
		interpretacia['Z' -'A'] = zapcha;      // premenna 'Z'
		Vyraz x = new Or(
				new Not(new Premenna('B')),
				new Premenna('Z')
				);
		System.out.println(x);  // "(-B | Z)"
		return x.eval(interpretacia);
	}
	
	/**
	 * Vypocita pravdepodobnost ze budem mat Acko z Javy. Acko ziskam ked chodim
	 * na cvika, robim dobre ulohy a spravim dobre skusku.
	 * @param cvika ucast na cvikach
	 * @param ulohy uspesnost na ulohach
	 * @param skuska pravdepodobnost, ze spravim dobre skusku
	 * @return pravdepodobnost ze budem mat Acko z Javy
	 */
	public static double acko(double cvika, double ulohy, double skuska) {
		double[] interpretacia = new double[26];
		interpretacia['C' -'A'] = cvika;  // premenna 'C'
		interpretacia['U' -'A'] = ulohy;      // premenna 'U'
		interpretacia['S' -'A'] = skuska;      // premenna 'S'
		Vyraz x = new And(new And(new Premenna('C'), new Premenna('U')), new Premenna('S'));
		System.out.println(x);  //
		return x.eval(interpretacia);
	}
	
	/**
	 * Vypocita pravdepodobnost, ze nespravim predmet Prg4. Neprejdem ked
	 * nechodim na cvika, alebo ked: nerobim dobre ulohy a zaroven nespravim
	 * dobre skusku.
	 * @param cvika ucast na cvikach
	 * @param ulohy uspesnost na ulohach
	 * @param skuska pravdepodobnost, ze spravim dobre skusku
	 * @return pravdepodobnost ze nespravim predmet Prg4
	 */
	public static double nespravimJavu(double cvika, double ulohy, double skuska) {
		double[] interpretacia = new double[26];
		interpretacia['C' -'A'] = cvika;  // premenna 'C'
		interpretacia['U' -'A'] = ulohy;      // premenna 'U'
		interpretacia['S' -'A'] = skuska;      // premenna 'S'
		Vyraz x = new Or(  new Not(new Premenna('C')),   new And( new Not(new Premenna('U')), new Not(new Premenna('S')))     );
		Vyraz y = new Not(new And(new Premenna('C'), new Premenna('U')));
		System.out.println(x);  //
		return x.eval(interpretacia);
	}
	
	public void zlyVstup() {
		throw new IllegalArgumentException("Zly vstup!");
	}
	
	
	
	public static void main(String[] args) {
		// tu si to mozete testovat
		double budikZvoni = 0.9;
		double zapcha = 0.6;
		System.out.println("Budem mestakt na " + budemMeskat(budikZvoni, zapcha)*100 + "%");  //64%

		double cvika = 0.9;
		double ulohy = 0.6;
		double skuska = 0.9;
		System.out.println("Budem mat A na " + acko(cvika, ulohy, skuska)*100 + "%");

		double cvika2 = 0.9;
		double ulohy2 = 0.9;
		double skuska2 = 0.9;
		System.out.println("Nespravim Javu " + acko(cvika2, ulohy2, skuska2)*100 + "%");
	}

}

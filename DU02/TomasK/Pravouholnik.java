public class Pravouholnik extends Utvar {
	private double dlzka, sirka;
	private Bod bod;
	
	public Pravouholnik(Bod lh, double sx, double sy) {
		super();
		dlzka = sx;
		sirka = sy;
		bod = lh;
	}

	@Override
	double obsah() {
		return dlzka * sirka;
	}

	@Override
	double obvod() {
		return 2 * dlzka + 2 * sirka;
	}

	@Override
	boolean obsahuje(Bod p) {
		return ((bod.getX() <= p.getX() && p.getX() <= bod.getX() + dlzka) &&
				(bod.getY() <= p.getY() && p.getY() <= bod.getY() + sirka));
	}
}

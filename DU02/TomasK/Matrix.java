import java.util.Arrays;

public class Matrix {

	public static void main(String[] args) {
//		double[][] pole = {{1, 2, -3},{4, 5, 6}};
//		double[][] pole1 = {{1, 2}, {3,4}, {-5, 6}};
//		Matrix m1 = new Matrix(pole);
//		Matrix m2 = new Matrix(pole1);
//		Matrix m3 = m1.times(m2);
//		System.out.println(m3.toString());


		Point p = new Point(2, 3);
		System.out.println("p =\n" + p);
		Transformation tr = new Translate(1, -2);
		Transformation rot = new Rotate(Math.PI / 2);
		Transformation tr2 = new Translate(3, 2);
		Point q = tr2.apply(rot.apply(tr.apply(p)));
		System.out.println("q =\n" + q);	// bod x=2,y=5 , t.j. matica [[2], [5], [1]]
		Point pp = q.transform(tr2.inverse())
					.transform(rot.inverse())
					.transform(tr.inverse());
		System.out.println("pp =\n" + pp); // rovnake ako p
	}
	
	
	
	
	
	/** Obsah matice */
	protected final double[][] m;
	
	/** Pocet riadkov a stlpcov matice */
	public final int rows, cols;

	/**
	 * Vytvori maticu podla vstupneho pola, pricom kontroluje zle vstupy.
	 * @param m obsah matice, musi byt obdlznikove pole
	 */
	public Matrix(double[][] m) {
		if(m == null || m.length < 1 || m[0] == null || m[0].length < 1){
			zlyVstup();
		}
		rows = m.length;
		cols = m[0].length;
		this.m = new double[rows][cols];
		for(int r = 0; r < rows; r++){
			if(m[r] == null || m[r].length != cols){
				zlyVstup();
			}
			for(int c = 0; c < cols; c++){
				this.m[r][c] = m[r][c];
			}
		}
	}

	/**
	 * Vytvori maticu rozmerov [rows x cols] so samymi nulami.
	 */
	public Matrix(int rows, int cols) {
		m = new double[rows][cols];
		this.rows = rows;
		this.cols = cols;
	}

	/**
	 * Vytvori jednotkovu maticu velkosti [size x size], s jednotkami iba na
	 * diagonale.
	 */
	public Matrix(int size) {
		this(size, size);
		for(int i = 0; i < size; i++){
			m[i][i] = 1;
		}
	}

	/**
	 * @return cislo na riadku row a stlpci col
	 */
	public double get(int row, int col) {
		return m[row][col];
	}

	/**
	 * Scita tuto maticu s inou.
	 * @param otherMatrix matica, ktoru budeme pricitavat
	 * @return nova matica, ktora sa rovna this + otherMatrix
	 */
	public Matrix plus(Matrix otherMatrix) {
		if(otherMatrix == null || rows != otherMatrix.rows || cols != otherMatrix.cols){
			zlyVstup();
		}

		Matrix result = new Matrix(rows, cols);
		for(int r = 0; r < rows; r++){
			for(int c = 0; c < cols; c++){
				result.m[r][c] = get(r, c) + otherMatrix.get(r, c);
			}
		}

		return result;
	}

	/**
	 * Vynasobi tuto maticu s inou.
	 * @param otherMatrix matica, ktorou budeme nasobit
	 * @return nova matica, ktora sa rovna this * otherMatrix
	 */
	public Matrix times(Matrix otherMatrix) {
		if (otherMatrix == null || this == null) {
			zlyVstup();
		}
		if (otherMatrix.rows !=  this.cols) {
			System.out.println("hola!");
			System.out.println("this =\n" + this.toString());
				System.out.println("other =\n" + otherMatrix.toString());
			zlyVstup();
		}

		Matrix novaM = new Matrix(new double[this.rows][otherMatrix.cols]);
//		System.out.println("this =\n" + this.toString());
//		System.out.println("other =\n" + otherMatrix.toString());

		for (int i = 0; i < novaM.rows; i++) {

			for (int j = 0; j < novaM.cols; j++) {
				double sum = 0;

				for (int k = 0; k < this.cols; k++) {
					sum += this.m[i][k] * otherMatrix.m[k][j];
				}

				novaM.m[i][j] = sum;

			}

		}
		System.out.println("m1 =\n" + this.toString());
		System.out.println("m2 =\n" + otherMatrix.toString());
		System.out.println("nova =\n" + novaM.toString());
		return novaM;
	}
	public boolean MatrixCheck(Matrix m) {
		if (m == null) {
			return false;
		}
		return true;
	}
	
	

	@Override
	public String toString() {
		StringBuffer result = new StringBuffer();
		for(int r = 0; r < m.length; r++){
			result.append(Arrays.toString(m[r])).append('\n');
		}
		return result.toString();
	}
	
	

	public void zlyVstup() {
		throw new IllegalArgumentException("Zly vstup!");
	}

}

import java.util.Arrays;

public class Matrix {

	public static void main(String[] args) {
		Point p = new Point(2, 3);
		System.out.println("p =\n" + p);
		Transformation tr = new Translate(1, -2);
		Transformation rot = new Rotate(Math.PI / 2);
		Transformation tr2 = new Translate(3, 2);
//		Point q = tr.apply(p);          //   bod 2 3 1       2 + 0 + 0      2  3
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
		if(otherMatrix == null || m == null || otherMatrix.m == null || otherMatrix.rows == 0 || otherMatrix.cols == 0 ||
				m[0].length != otherMatrix.m.length){
			zlyVstup();
		}

		double[][] result = new double[rows][otherMatrix.cols];
		for (int i = 0; i < rows; i++) {   // pocet riadkov prveho
			for (int j = 0; j < otherMatrix.cols; j++) {   // pocet stlpcov prveho riadku DRUHEJ matice
				result[i][j] = multiplyMatricesCell(m, otherMatrix.m, i, j);  // inspirovane zo stranky: https://www.baeldung.com/java-matrix-multiplication
			}
		}
		return new Matrix(result);
	}

	double multiplyMatricesCell(double[][] firstMatrix, double[][] otherMatrix, int row, int col) { // inspirovane zo stranky: https://www.baeldung.com/java-matrix-multiplication
		double cell = 0;
		for (int i = 0; i < firstMatrix[0].length; i++) {   // 3 krat
			cell += firstMatrix[row][i] * otherMatrix[i][col];
		}
		return cell;
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

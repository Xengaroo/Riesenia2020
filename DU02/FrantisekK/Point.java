import java.util.Arrays;

public class Point extends Matrix{

    public Point(double x, double y) {
        super(new double[][]{{x}, {y}, {1}});

    }
    public Point(Matrix matrix)  // vytvori Point z matice o velkosti 3 x 1
    {
        super(matrix.m);
    }

    public Point transform(Transformation t)  // aplikuje trans. t na tento bod, vrati vysledok
    {
        Point p = new Point(m[0][0], m[1][0]);  // urobi kopiu bodu
        p = t.apply(p);
        return p;         // prekonvertuje maticu na mod
    }

}

public class Point extends Matrix {
    private double x;
    private double y;

    public Point(double x, double y) {
        super(3, 1);

        this.m[0][0] = x;
        this.m[1][0] = y;
        this.m[2][0] = 1;

        this.x = x;
        this.y = y;

    }
    public Point(Matrix matrix) {
        super(matrix.m);

        this.x = matrix.m[0][0];
        this.y = matrix.m[1][0];

    }
    public Point transform(Transformation t) {
        Point p = new Point(this.x, this. y);
        return t.apply(p);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
}

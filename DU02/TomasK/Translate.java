public class Translate extends Matrix implements Transformation {
    private double x;
    private double y;

    public Translate(double x, double y) {
        super(3, 3);
        for (int i = 0; i < 3; i++) {
            this.m[i][i] = 1;
            this.m[0][2] = x;
            this.m[1][2] = y;
        }
        this.x = x;
        this.y = y;

    }

    public Point apply(Point p) {
        Point newP = new Point(p.getX(),p.getY());

        return new Point(this.times(newP));
    }

    public Transformation inverse() {
        return new Translate(-this.x, -this.y);

    }


}

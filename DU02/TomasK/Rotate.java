public class Rotate extends Matrix implements Transformation {
    private double angle;

    public Rotate(double angle) {
        super(3, 3);
        this.m[0][0] = Math.cos(angle);
        this.m[0][1] = -Math.sin(angle);
        this.m[1][0] = Math.sin(angle);
        this.m[1][1] = Math.cos(angle);
        this.m[2][2] = 1;
        this.angle = angle;
    }

    public Point apply(Point p) {
        Point newP = new Point(p.getX(),p.getY());

        return new Point(this.times(newP));
    }

    public Transformation inverse() {
        return new Rotate(-this.angle);

    }


}

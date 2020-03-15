public class Rotate extends Matrix implements Transformation {
    double angle;
    Rotate(double angle) {
        super(new double[][]{
                {Math.cos(angle),-Math.sin(angle),0},
                {Math.sin(angle),Math.cos(angle),0},
                {0,0,1}});
        this.angle = angle;
    }


    @Override
    public Point apply(Point p) {
        return new Point(times(p));
    }

    @Override
    public Transformation inverse() {
        return new Rotate(-angle);
    }
}

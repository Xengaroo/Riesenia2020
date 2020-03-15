public class Translate extends Matrix implements Transformation {
    double x,y;

    Translate(double x, double y) {
        super(new double[][]{
                {1,0,x},
                {0,1,y},
                {0,0,1}});
        this.x = x;
        this.y = y;
    }

    @Override
    public Point apply(Point p) {
        return new Point(times(p));
    }

    @Override
    public Transformation inverse() {
        return new Translate(-x,-y);
    }
}

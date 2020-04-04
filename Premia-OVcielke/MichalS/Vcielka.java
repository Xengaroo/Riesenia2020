public class Vcielka {
    public static int goHome(String path) {
        if (path == null) return 0;

        String[] arr = path.split(",");
        double x = 0, y = 0;

        for (String s : arr) {
            switch (s) {
                case "n":
                    y += 2;
                    break;
                case "nw":
                    x -= Math.sqrt(3);
                    y += 1;
                    break;
                case "ne":
                    x += Math.sqrt(3);
                    y += 1;
                    break;
                case "s":
                    y -= 2;
                    break;
                case "sw":
                    x -= Math.sqrt(3);
                    y -= 1;
                    break;
                case "se":
                    x += Math.sqrt(3);
                    y -= 1;
                    break;
                default:
                    break;
            }
        }

        x = Math.abs(x);
        int steps = (int) Math.round(x / Math.sqrt(3));
        y = Math.abs(y);
        y -= steps;
        steps += Math.round(y / 2);
        return steps;
    }
}

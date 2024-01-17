public class DrawingShapes {
        public static void drawTriangle(int n) {
            String y = "*";
            int x = 1;
            while (x <= n) {
                System.out.println(y);
                y = y + "*";
                x++;
            };
        }
    public static void main(String[] args) {
        drawTriangle(10);
    }
}

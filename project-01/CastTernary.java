public class CastTernary {
    public static void main(String[] args) {
        double num1 = 9.65;
        int num2 = 25;

        int castNum1 = (int) num1;
        double autoNum2 = num2;

        System.out.println("(int)num1 = " + castNum1);
        System.out.println("num2自动转double = " + autoNum2);
        System.out.println("num1 + num2 = " + (num1 + num2));

        int x = 28;
        int y = 15;
        int maxXY = (x > y) ? x : y;
        System.out.println("x和y较大值 = " + maxXY);

        int a = 12;
        int b = 36;
        int c = 20;
        int maxABC = (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);
        System.out.println("a,b,c最大值 = " + maxABC);
    }
}

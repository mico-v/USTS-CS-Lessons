public class VariableCalculator {
    public static void main(String[] args) {
        int a = 18;
        int b = 5;
        float c = 3.8f;
        double d = 2.5;

        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("a / b = " + (a / b));
        System.out.println("a % b = " + (a % b));

        System.out.println("c + d = " + (c + d));
        System.out.println("(a + b) * c = " + ((a + b) * c));

        int i = 10;
        int j;
        j = i++;
        System.out.println("j = i++ => j=" + j + ", i=" + i);

        j = ++i;
        System.out.println("j = ++i => j=" + j + ", i=" + i);
    }
}

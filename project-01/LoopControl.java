public class LoopControl {
    public static void main(String[] args) {
        System.out.print("遍历1-10(continue@4, break@8): ");
        for (int i = 1; i <= 10; i++) {
            if (i == 4) {
                continue;
            }
            if (i == 8) {
                break;
            }
            System.out.print(i + " ");
        }
        System.out.println();

        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                sum += i;
            }
        }
        System.out.println("1-100中同时被3和5整除的数之和 = " + sum);
    }
}

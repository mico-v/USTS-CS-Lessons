public class BranchLoop {
    private static String seasonByMonth(int month) {
        switch (month) {
            case 3:
            case 4:
            case 5:
                return "春季";
            case 6:
            case 7:
            case 8:
                return "夏季";
            case 9:
            case 10:
            case 11:
                return "秋季";
            case 12:
            case 1:
            case 2:
                return "冬季";
            default:
                return "无效月份";
        }
    }

    public static void main(String[] args) {
        int month = 5;
        System.out.println("month=" + month + " => " + seasonByMonth(month));

        System.out.print("1-20奇数(for): ");
        for (int i = 1; i <= 20; i++) {
            if (i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();

        System.out.print("1-20偶数(while): ");
        int n = 1;
        while (n <= 20) {
            if (n % 2 == 0) {
                System.out.print(n + " ");
            }
            n++;
        }
        System.out.println();

        int sum = 0;
        int k = 1;
        do {
            sum += k;
            k++;
        } while (k <= 10);
        System.out.println("1-10累加和(do-while) = " + sum);
    }
}

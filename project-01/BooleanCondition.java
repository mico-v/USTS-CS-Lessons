public class BooleanCondition {
    private static String getGrade(int score) {
        if (score >= 90) {
            return "优秀";
        } else if (score >= 70) {
            return "良好";
        } else if (score >= 60) {
            return "及格";
        }
        return "不及格";
    }

    public static void main(String[] args) {
        boolean flag1 = true;
        boolean flag2 = false;

        System.out.println("!flag1 = " + (!flag1));
        System.out.println("flag1 & flag2 = " + (flag1 & flag2));
        System.out.println("flag1 | flag2 = " + (flag1 | flag2));
        System.out.println("flag1 ^ flag2 = " + (flag1 ^ flag2));
        System.out.println("flag1 && flag2 = " + (flag1 && flag2));
        System.out.println("flag1 || flag2 = " + (flag1 || flag2));

        int[] scores = {78, 55, 92, 66};
        for (int score : scores) {
            System.out.println("score=" + score + " => " + getGrade(score));
        }
    }
}

public class TestStudent {
    public static void main(String[] args) {
        Exp2Student s1 = new Exp2Student("张三", "2024001", 85, 90);

        Exp2Student s2 = new Exp2Student();
        s2.setName("李四");
        s2.setId("2024002");
        s2.setMathScore(72);
        s2.setEnglishScore(88);

        // 尝试设置非法成绩，验证边界检查
        s2.setMathScore(-10);

        s1.printInfo();
        s2.printInfo();
    }
}

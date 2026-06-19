public class Exp2Student {
    // 私有属性，体现封装
    private String name;
    private String id;
    private int mathScore;
    private int englishScore;

    // 带参构造方法
    public Exp2Student(String name, String id, int mathScore, int englishScore) {
        this.name = name;
        this.id = id;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    // 无参构造方法
    public Exp2Student() {
        this("未知", "000", 0, 0);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getMathScore() {
        return mathScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMathScore(int newScore) {
        if (newScore < 0 || newScore > 100) {
            System.out.println("错误：成绩必须在0-100之间！");
            return;
        }
        this.mathScore = newScore;
    }

    public void setEnglishScore(int newScore) {
        if (newScore < 0 || newScore > 100) {
            System.out.println("错误：成绩必须在0-100之间！");
            return;
        }
        this.englishScore = newScore;
    }

    public int computeAverage() {
        return (mathScore + englishScore) / 2;
    }

    public void printInfo() {
        System.out.println(
            "学号: " + id + ", 姓名: " + name + ", 数学: " + mathScore + ", 英语: " + englishScore + ", 平均分: " + computeAverage()
        );
    }
}

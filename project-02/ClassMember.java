public class ClassMember {
    private String name;
    private int memberId;

    private static int totalCount = 0;
    private static int nextId = 1;

    public ClassMember(String name) {
        this.name = name;
        this.memberId = nextId;
        nextId++;
        totalCount++;
    }

    public void introduce() {
        System.out.println("大家好，我是 " + name + "，编号 " + memberId);
    }

    public static int getTotalCount() {
        return totalCount;
    }

    public static void printClassInfo() {
        System.out.println("当前班级共有 " + totalCount + " 名成员");
    }

    // 思考题1：若 totalCount 变为实例变量，每个对象各自维护一份，无法反映全班总人数。
    // 思考题2：static 方法不依赖具体对象，因此不能直接访问实例变量 name。
}

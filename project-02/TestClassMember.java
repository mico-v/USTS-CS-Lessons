public class TestClassMember {
    public static void main(String[] args) {
        ClassMember.printClassInfo();

        ClassMember m1 = new ClassMember("小明");
        ClassMember m2 = new ClassMember("小红");
        ClassMember m3 = new ClassMember("小华");

        m1.introduce();
        m2.introduce();
        m3.introduce();

        ClassMember.printClassInfo();
    }
}

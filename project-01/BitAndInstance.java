class Person {
}

class Student extends Person {
}

public class BitAndInstance {
    public static void main(String[] args) {
        int m = 6; // 0110
        int n = 3; // 0011

        System.out.println("m & n = " + (m & n));   // 0010 => 2
        System.out.println("m | n = " + (m | n));   // 0111 => 7
        System.out.println("m ^ n = " + (m ^ n));   // 0101 => 5
        System.out.println("~m = " + (~m));         // 按位取反
        System.out.println("m << 1 = " + (m << 1)); // 左移1位 => 12
        System.out.println("m >> 1 = " + (m >> 1)); // 右移1位 => 3

        Person p = new Student();
        Student s = new Student();

        System.out.println("p instanceof Person = " + (p instanceof Person));
        System.out.println("p instanceof Student = " + (p instanceof Student));
        System.out.println("s instanceof Person = " + (s instanceof Person));
        System.out.println("s instanceof Student = " + (s instanceof Student));
    }
}

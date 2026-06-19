public class TestPoint {
    static void changeValue(int val) {
        val = 100;
        System.out.println("方法内 val = " + val);
    }

    static void changePoint(Point p) {
        p.x = 100;
        p.y = 200;
        System.out.println("方法内 p = ");
        p.print();
    }

    static void replacePoint(Point p) {
        p = new Point(999, 999);
        System.out.println("方法内重定向后 p = ");
        p.print();
    }

    public static void main(String[] args) {
        int num = 10;
        changeValue(num);
        System.out.println("调用后 num = " + num); // 预测：10
        System.out.println("---");

        Point p1 = new Point(1, 2);
        changePoint(p1);
        System.out.println("调用后 p1: ");
        p1.print(); // 预测：Point(100, 200)
        System.out.println("---");

        Point p2 = new Point(3, 4);
        replacePoint(p2);
        System.out.println("调用后 p2: ");
        p2.print(); // 预测：Point(3, 4)

        // 说明：Java 只有值传递。引用类型传的是“地址值副本”。
        // 测试2中副本和实参都指向同一对象，改属性会生效；
        // 测试3中只是让形参副本改指向新对象，不影响实参引用。
    }
}

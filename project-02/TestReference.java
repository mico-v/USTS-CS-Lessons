public class TestReference {
    public static void main(String[] args) {
        // 实验 A：引用赋值（共享同一对象）
        Point a = new Point(1, 2);
        Point b = a;
        b.x = 10;
        System.out.println("a: ");
        a.print(); // 预测：Point(10, 2)
        System.out.println("b: ");
        b.print();
        System.out.println("a == b : " + (a == b)); // 预测：true
        System.out.println("---");

        // 实验 B：创建新对象（手动复制）
        Point c = new Point(1, 2);
        Point d = new Point(c.x, c.y);
        d.x = 10;
        System.out.println("c: ");
        c.print(); // 预测：Point(1, 2)
        System.out.println("d: ");
        d.print();
        System.out.println("c == d : " + (c == d)); // 预测：false
        System.out.println("---");

        // 实验 C：引用重新绑定
        Point e = new Point(1, 2);
        Point f = e;
        f = new Point(5, 6);
        System.out.println("e: ");
        e.print(); // 预测：Point(1, 2)
        System.out.println("f: ");
        f.print();

        // 总结：
        // 1) 基本类型的 = 是拷贝值。
        // 2) 引用类型的 = 是拷贝地址值（引用），不是复制对象。
    }
}

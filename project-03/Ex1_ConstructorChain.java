/**
 * 实验一：继承与构造方法链
 *
 * 【学习目标】
 *   - 掌握 extends 关键字的使用
 *   - 理解父类和子类构造方法的调用顺序（构造方法链）
 *   - 掌握 super() 调用父类构造方法的用法
 *
 * 【任务说明】
 *   下面定义了一个简单的"交通工具"继承体系：Vehicle → Car → ElectricCar
 *   请根据注释中的 TODO 提示，补全代码，使程序输出：
 *
 *   --- 创建 ElectricCar 对象 ---
 *   Vehicle 构造方法被调用，品牌：比亚迪
 *   Car 构造方法被调用，座位数：5
 *   ElectricCar 构造方法被调用，电池容量：60kWh
 *   --- 车辆信息 ---
 *   品牌：比亚迪
 *   座位数：5
 *   电池容量：60kWh
 */

// ======================== 父类：Vehicle ========================
class Vehicle {
    String brand;

    // TODO 1: 编写 Vehicle 的有参构造方法
    //   - 参数：String brand
    //   - 将参数赋值给 this.brand
    //   - 打印："Vehicle 构造方法被调用，品牌：" + brand
    Vehicle(String brand) {
        this.brand = brand;
        System.out.println("Vehicle 构造方法被调用，品牌：" + brand);
    }

}

// ======================== 子类：Car ========================
// TODO 2: 让 Car 类继承 Vehicle（使用 extends 关键字）
class Car extends Vehicle {
    int seats;

    // TODO 3: 编写 Car 的有参构造方法
    //   - 参数：String brand, int seats
    //   - 第一行必须用 super() 调用父类构造方法，传入 brand
    //   - 将 seats 赋值给 this.seats
    //   - 打印："Car 构造方法被调用，座位数：" + seats
    Car(String brand, int seats) {
        super(brand);
        this.seats = seats;
        System.out.println("Car 构造方法被调用，座位数：" + seats);
    }

}

// ======================== 子类：ElectricCar ========================
// TODO 4: 让 ElectricCar 类继承 Car
class ElectricCar extends Car {
    String batteryCapacity;

    // TODO 5: 编写 ElectricCar 的有参构造方法
    //   - 参数：String brand, int seats, String batteryCapacity
    //   - 第一行用 super() 调用父类 Car 的构造方法
    //   - 将 batteryCapacity 赋值给 this.batteryCapacity
    //   - 打印："ElectricCar 构造方法被调用，电池容量：" + batteryCapacity
    ElectricCar(String brand, int seats, String batteryCapacity) {
        super(brand, seats);
        this.batteryCapacity = batteryCapacity;
        System.out.println("ElectricCar 构造方法被调用，电池容量：" + batteryCapacity);
    }

}

// ======================== 测试类 ========================
public class Ex1_ConstructorChain {
    public static void main(String[] args) {
        System.out.println("--- 创建 ElectricCar 对象 ---");
        ElectricCar ec = new ElectricCar("比亚迪", 5, "60kWh");

        System.out.println("--- 车辆信息 ---");
        System.out.println("品牌：" + ec.brand);
        System.out.println("座位数：" + ec.seats);
        System.out.println("电池容量：" + ec.batteryCapacity);
    }
}

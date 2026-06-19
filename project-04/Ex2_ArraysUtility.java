import java.util.Arrays;

/**
 * 实验四 - 题目 2：Arrays 工具类的使用
 *
 * 本题练习 java.util.Arrays 工具类的常用方法：
 *   Arrays.toString()     —— 将数组转为可读字符串便于打印
 *   Arrays.sort()         —— 对数组升序排序
 *   Arrays.binarySearch() —— 在"有序数组"中二分查找
 *   Arrays.copyOf()       —— 拷贝数组（可改变长度）
 *   Arrays.fill()         —— 用指定值填充数组
 *
 * 使用说明：将下方每一处 /* TODO x *&#47; 替换为你自己的代码。完成后运行程序，
 * 并与文件末尾"期望输出"进行对照。
 */
public class Ex2_ArraysUtility {

    public static void main(String[] args) {

        int[] data = {34, 12, 78, 5, 63, 21, 47};

        // ========== TODO 1：Arrays.toString() 打印原数组 ==========
        // 使用 Arrays.toString() 将 data 转成字符串，并拼接上前缀"排序前：" 再打印
        System.out.println("排序前：" + Arrays.toString(data));

        // ========== TODO 2：Arrays.sort() 升序排序 ==========
        // 调用 Arrays.sort() 对 data 进行升序排序（原地修改，无返回值）
        Arrays.sort(data);
        System.out.println("排序后：" + Arrays.toString(data));

        // ========== TODO 3：Arrays.binarySearch() 二分查找 ==========
        // 在排序后的 data 中查找元素 47，把返回的下标赋给 index
        // 注意：binarySearch 的前提是数组"已经有序"！
        int index = Arrays.binarySearch(data, 47) /* TODO 3 */ ;
        System.out.println("元素 47 在排序后数组中的下标 = " + index);

        // ========== TODO 4：Arrays.copyOf() 扩容复制 ==========
        // 用 Arrays.copyOf() 把 data 扩容到长度 10，结果赋给 bigger
        // 多出来的位置会被自动填充为 int 的默认值 0
        int[] bigger = Arrays.copyOf(data, 10) /* TODO 4 */ ;
        System.out.println("扩容后：" + Arrays.toString(bigger));

        // ========== TODO 5：Arrays.fill() 填充数组 ==========
        // 用 Arrays.fill() 把长度为 5 的 buffer 全部填充为 -1
        int[] buffer = new int[5];
        /* TODO 5 */
        Arrays.fill(buffer, -1);
        System.out.println("填充后：" + Arrays.toString(buffer));
    }

    /*
     * =================== 期望输出 ===================
     * 排序前：[34, 12, 78, 5, 63, 21, 47]
     * 排序后：[5, 12, 21, 34, 47, 63, 78]
     * 元素 47 在排序后数组中的下标 = 4
     * 扩容后：[5, 12, 21, 34, 47, 63, 78, 0, 0, 0]
     * 填充后：[-1, -1, -1, -1, -1]
     * ================================================
     */
}

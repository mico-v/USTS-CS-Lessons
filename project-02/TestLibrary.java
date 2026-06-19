public class TestLibrary {
    public static void main(String[] args) {
        Library.Book book1 = new Library.Book("Java编程思想", "Bruce Eckel");
        Library.Book book2 = new Library.Book("数据结构", "严蔚敏");
        new Library.Book("计算机网络", "谢希仁");

        Library library = new Library("北京交通大学图书馆");

        library.borrowBook(book1, "张三");
        library.borrowBook(book2, "李四");

        System.out.println("图书馆共有 " + Library.getTotalBooks() + " 本书");
    }
}

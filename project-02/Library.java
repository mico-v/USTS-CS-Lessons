public class Library {
    private String libraryName;
    private static int totalBooks = 0;

    public Library(String libraryName) {
        this.libraryName = libraryName;
    }

    // 静态嵌套类
    public static class Book {
        private String title;
        private String author;

        public Book(String title, String author) {
            this.title = title;
            this.author = author;
            totalBooks++;
        }

        public void showInfo() {
            System.out.println("《" + title + "》 作者: " + author);
        }
    }

    // 内部类（非静态）
    public class BorrowRecord {
        private Book book;
        private String borrower;

        public BorrowRecord(Book book, String borrower) {
            this.book = book;
            this.borrower = borrower;
        }

        public void printRecord() {
            System.out.println(borrower + " 从 [" + libraryName + "] 借出了：");
            book.showInfo();
        }
    }

    public void borrowBook(Book book, String borrower) {
        BorrowRecord record = new BorrowRecord(book, borrower);
        record.printRecord();
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    // 思考题：
    // 1) Book 可用 Library.Book 直接创建；BorrowRecord 必须依附具体 Library 实例。
    // 2) Book 不能直接访问 libraryName，因为它不持有外部类实例引用。
    // 3) BorrowRecord 能访问 libraryName，因为内部类隐式持有外部类 this。
}

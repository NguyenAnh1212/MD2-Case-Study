package view;

import controller.BookManager;
import model.Adventure;
import model.Book;
import model.Fiction;
import storage.BookFile;

import java.io.IOException;
import java.util.*;

public class Admin {
    private static ArrayList<Book> booksAdmin = BookManager.books;

    public static void main(String[] args) throws IOException {
        // Tên đăng nhập: admin
        // Mật khẩu: 123456
        System.out.println("Nhập tên đăng nhập: ");
        String name = "admin";
        Scanner sc = new Scanner(System.in);
        String inputName = sc.nextLine();
        if (name.equals(inputName)){
            System.out.println("Nhập mật khẩu: ");
            String password = "123456";
            Scanner scanner = new Scanner(System.in);
            String inputPassword = scanner.nextLine();
            if (password.equals(inputPassword)){
                dispayMenu();
            }
        } else {
            System.out.println("Tên đăng nhập sai");
        }

    }

    private static void dispayMenu() throws IOException {
        int choice = -1;
        Scanner inputChoice = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("________Menu_________");
            System.out.println("1. Hiển thị sách trong thư viện");
            System.out.println("2. Thêm mới sách ");
            System.out.println("3. Xóa sách theo mã sách");
            System.out.println("4. Sửa thông tin sách");
            System.out.println("0. Exit");
            System.out.print("Lựa chọn của bạn: ");
            choice = inputChoice.nextInt();
            switch (choice) {
                case 1:
                    showAllBook();
                    break;
                case 2:
                    addNewBook();
                    break;
                case 3:
                    deleteBookByCode();
                    break;
                case 4:
                    editBookByCode();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Mời nhập lựa chọn");
            }
        }
    }

    public static void showAllBook() {
        Collections.sort(booksAdmin);
        if (booksAdmin.size() == 0) {
            System.out.println("Danh sách trống");
        }
        for (Book b : booksAdmin
        ) {
            System.out.println(b);
        }
    }

    public static void deleteBookByCode() throws IOException {
        String inputCode;
        System.out.println("Nhập mã sách mà bạn muốn xóa.");
        Scanner sc = new Scanner(System.in);
        inputCode = sc.nextLine();
        int index = BookManager.getIndexByCode(inputCode);
        if (index == -1) {
            System.out.println("Không tồn tại mã sách trong hệ thống.");
        } else {
            BookManager.deleteBookByCode(index);
            System.out.println("Mã sách " + inputCode + " đã được xóa.");
        }
        BookFile.writeFile(booksAdmin);
    }

    public static void addNewBook() {
        BookManager.books = booksAdmin;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số sách bạn muốn thêm: ");
        int number = scanner.nextInt();
        for (int i = 0; i < number; i++) {
            System.out.println("Nhập 1 để thêm sách viễn tưởng, nhập 2 để thêm sách phiêu lưu.");
            try {
                int choice = scanner.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Mời nhập lại từ đầu.");
                    addNewBook();
                }
                Book newBook = creatNewBook(choice);
                BookManager.addNewBook(newBook);
                try {
                    BookFile.writeFile(booksAdmin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (InputMismatchException e){
                System.out.println("Dữ liệu nhập không hợp lệ, mời nhập lại");

            }
        }
    }

    public static void editBookByCode() {
        BookManager.books = booksAdmin;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã sách bạn muốn sửa: ");
        String bookCode = sc.nextLine();
        int index = BookManager.getIndexByCode(bookCode);
        if (index == -1) {
            System.out.println("Mã sách không tồn tại trên hệ thống");
        }
        int choice;
        Book editBook = BookManager.books.get(index);
        if (editBook instanceof Fiction) {
            choice = 1;
        } else {
            choice = 2;
        }
        Book newbook = creatEditBook(choice);
        BookManager.editBookByCode(index, newbook);
        try {
            BookFile.writeFile(booksAdmin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String checkInputBookCode1() {
        Scanner scanner = new Scanner(System.in);
        String bookCode = scanner.nextLine();
        if (!Regex.isValidCode(bookCode)) {
            System.err.println("Mã sách không hợp lệ, xin mời nhập lại");
            checkInputBookCode();
        }
        return bookCode;
    }


    public static String checkInputBookCode() {
        Scanner scanner = new Scanner(System.in);
        String bookCode = scanner.nextLine();
        if (BookManager.getIndexByCode(bookCode) != -1) {
            System.out.println("Mã sách này đã tồn tại, xin mời nhập lại.");
            return checkInputBookCode();
        }
        if (!Regex.isValidCode(bookCode)) {
            System.err.println("Mã sách không hợp lệ, xin mời nhập lại");
            return checkInputBookCode();
        }
        return bookCode;
    }


    public static String checkInputAuthor() {
        Scanner scanner = new Scanner(System.in);
        String author = scanner.nextLine();
        if (!Regex.isValidAuthor(author)) {
            System.err.println("Tên tác giả không hợp lệ, xin mời nhập lại");
            checkInputAuthor();
        }
        return author;
    }

    public static int checkInputPrice() {
        Scanner scanner = new Scanner(System.in);
        int price = scanner.nextInt();
        if (!Regex.isValidPrice(price + "")) {
            System.err.println("Giá sách không hợp lệ, xin mời nhập lại");
            checkInputPrice();
        }
        return price;
    }

    public static int checkInputStock() {
        Scanner scanner = new Scanner(System.in);
        int stock = scanner.nextInt();
        if (!Regex.isValidStock(stock + "")) {
            System.err.println("Số lượng tồn không hợp lệ, xin mời nhập lại");
            checkInputStock();
        }
        return stock;
    }

    public static String checkInputClassification() {
        Scanner scanner = new Scanner(System.in);
        String classification = scanner.nextLine();
        if (!Regex.isValidAuthor(classification)) {
            System.err.println("Đối tượng độc giả nhập không hợp lệ, xin mời nhập lại");
            checkInputClassification();
        }
        return classification;
    }

    public static String checkInputNational() {
        Scanner scanner = new Scanner(System.in);
        String national = scanner.nextLine();
        if (!Regex.isValidAuthor(national)) {
            System.err.println("Quốc gia nhập không hợp lệ, xin mời nhập lại");
            checkInputNational();
        }
        return national;
    }
    public static Book creatEditBook(int choice){
        Book newBook;
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập mã sách mới: ");
        String bookCode = checkInputBookCode1();

        System.out.println("Nhập tên sách mới: ");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();

        System.out.println("Nhập tên tác giả mới: ");
        Scanner scanner3 = new Scanner(System.in);
        String author = checkInputAuthor();

        System.out.println("Nhập giá sách mới: ");
        Scanner scanner4 = new Scanner(System.in);
        int price = checkInputPrice();

        System.out.println("Nhập số lượng sách mới: ");
        Scanner scanner5 = new Scanner(System.in);
        int stock = checkInputStock();

        if (choice == 1) {
            System.out.println("Nhập đối tượng độc giả: ");
            Scanner scanner6 = new Scanner(System.in);
            String classification = checkInputClassification();

            newBook = new Fiction(bookCode, name, author, price, stock, classification);
        } else {
            System.out.println("Nhập tên quốc gia: ");
            Scanner scanner6 = new Scanner(System.in);
            String national = checkInputNational();

            newBook = new Adventure(bookCode, name, author, price, stock, national);
        }
        return newBook;

    }

    public static Book creatNewBook(int choice) {
        Book newBook;
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Nhập mã sách: ");
        String bookCode = checkInputBookCode();

        System.out.println("Nhập tên sách: ");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.nextLine();

        System.out.println("Nhập tên tác giả: ");
        Scanner scanner3 = new Scanner(System.in);
        String author = checkInputAuthor();

        System.out.println("Nhập giá sách: ");
        Scanner scanner4 = new Scanner(System.in);
        int price = checkInputPrice();

        System.out.println("Nhập số lượng sách: ");
        Scanner scanner5 = new Scanner(System.in);
        int stock = checkInputStock();

        if (choice == 1) {
            System.out.println("Nhập đối tượng độc giả: ");
            Scanner scanner6 = new Scanner(System.in);
            String classification = checkInputClassification();

            newBook = new Fiction(bookCode, name, author, price, stock, classification);
        } else {
            System.out.println("Nhập tên quốc gia: ");
            Scanner scanner6 = new Scanner(System.in);
            String national = checkInputNational();

            newBook = new Adventure(bookCode, name, author, price, stock, national);
        }
        return newBook;
    }



    static class RegexTest {
        public static Regex tool = new Regex();
        public static String[] bookCodeEx = {"msd", "M05", "A005", "F01", "0051"};
    //    public static String[] name = {"@haiha", "hải hà", "minh", "/khu 12", "khu 115"};
        public static String[] author = {"@haiha", "hải hà", "minh", "/khu 12", "khu 115", "Ha Ha", "Mui Minh", "bảo Bình", "Bình Hoàng"};
        public static String[] price = {"12000.00", "1500000000", "015,200", "0000000", "da0051"};
        public static String[] stock = {"0", "10000", "A005", "100", "0000"};

        public static void main(String[] args) {
            for (String e: bookCodeEx
            ) {
                System.out.print("\n"+ e + " is valid? ");
                System.out.print(tool.isValidCode(e)+"\n");
            }

    //        for (String e: name
    //        ) {
    //            System.out.print("\n"+ e + " is valid? ");
    //            System.out.print(tool.isValidName(e)+"\n");
    //        }

            for (String e: author
            ) {
                System.out.print("\n"+ e + " is valid? ");
                System.out.print(tool.isValidAuthor(e)+"\n");
            }

            for (String e: price
            ) {
                System.out.print("\n"+ e + " is valid? ");
                System.out.print(tool.isValidPrice(e)+"\n");
            }

            for (String e: stock
            ) {
                System.out.print("\n" + e + " is valid? ");
                System.out.print(tool.isValidStock(e) + "\n");
            }
        }
    }
}

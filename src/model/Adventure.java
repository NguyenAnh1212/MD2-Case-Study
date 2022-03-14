package model;

public class Adventure extends Book {
    private String national;

    public Adventure() {
    }

    public Adventure(String national) {
        this.national = national;
    }

    public Adventure(String bookCode, String name, String author, int price, int stock, String national) {
        super(bookCode, name, author, price, stock);
        this.national = national;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    @Override
    public String toString() {
        return "Sách phiêu lưu {" + super.toString() +
                "Quốc gia: '" + national + '\'' +
                '}';
    }
}

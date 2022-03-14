package model;

public class Fiction extends Book {
    private String classification;

    public Fiction() {
    }

    public Fiction(String classification) {
        this.classification = classification;
    }

    public Fiction(String bookCode, String name, String author, int price, int stock, String classification) {
        super(bookCode, name, author, price, stock);
        this.classification = classification;
    }


    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return  "Sách viễn tưởng {" + super.toString() + "," +
                " Đối tượng độc giả: '" + classification + '\'' +
                '}';
    }
}

package model;

import java.io.Serializable;

public class Book implements Comparable<Book>, Serializable {
    private String bookCode;
    private String name;
    private String author;
    private int price;
    private int stock;


    public Book() {
    }

    public Book(String bookCode, String name, String author, int price, int stock) {
        this.bookCode = bookCode;
        this.name = name;
        this.author = author;
        this.price = price;
        this.stock = stock;
    }

    @Override
    public int compareTo(Book o) {
        return this.getBookCode().compareTo(o.getBookCode());
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return
                "Mã sách: '" + bookCode + '\'' +
                ", Tên sách:  '" + name + '\'' +
                ", Tác giả: '" + author + '\'' +
                ", Giá: " + price +
                ", Tồn kho: " + stock ;
    }
}

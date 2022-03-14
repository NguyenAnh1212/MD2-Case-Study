package controller;

import model.Book;
import storage.BookFile;

import java.io.IOException;
import java.util.ArrayList;

public class BookManager {
    public static ArrayList<Book> books = BookFile.readFile();

    public static void showAllBook(){
        if (books.size() == 0){
            System.out.println("Không có cuốn sách nào trong kho để hiển thị.");
        }
        else{
            for (Book b: books
                 ) {
                System.out.println(b);
            }
        }
    }

    public static void addNewBook(Book newBook){
        books.add(newBook);
    }

    public static void editBookByCode(int index, Book newBook){
        books.set(index, newBook);
    }

    public static void deleteBookByCode(int index){
        books.remove(index);

    }

    public static int getIndexByCode(String inputCode){
        int index = -1;
        for (int i = 0; i < books.size(); i++) {
            String bookCode = books.get(i).getBookCode();
            if (inputCode.equals(bookCode)) {
                index = i;
            }
        }
        return index;
    }

    public static int getIndexByName(String inputName){
        int index = -1;
        for (int i = 0; i < books.size(); i++) {
            String bookName = books.get(i).getName();
            if (inputName.equals(bookName)) {
                index = i;
            }
        }
        return index;
    }








}

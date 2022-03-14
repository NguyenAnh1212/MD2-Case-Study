package view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    private static final String regexbookCode = "^[F,A]\\d{3}" ;
//    private static final String regexname = "^[a-zA-Z0-9 ]*$";
    private static final String regexauthor =  "^[a-zA-Z\\s]+";
    private static final String regexprice = "^[0-9]+$";
    private static final String regexstock = "\\d{0,}";

     public static boolean isValidCode(String bookCode){
        Pattern p = Pattern.compile(regexbookCode);
        Matcher m = p.matcher(bookCode);
        return m.matches();
    }
//    public static boolean isValidName(String name){
//        Pattern p = Pattern.compile(regexname);
//        Matcher m = p.matcher(name);
//        return m.matches();
//    }

    public static boolean isValidAuthor(String author){
        Pattern p = Pattern.compile(regexauthor);
        Matcher m = p.matcher(author);
        return m.matches();
    }

    public static boolean isValidPrice(String price){
        Pattern p = Pattern.compile(regexprice);
        Matcher m = p.matcher(price);
        return m.matches();
    }

    public static boolean isValidStock(String stock){
        Pattern p = Pattern.compile(regexstock);
        Matcher m = p.matcher(stock);
        return m.matches();
    }
}

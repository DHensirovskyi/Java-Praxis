package CRUD;

import java.util.HashMap;
import java.util.Map;

public class BookManager {
    Map<Integer, Book> books = new HashMap<>();

    public BookManager(){}

    // Create
    public void addBook(Book book){
        if(books.containsKey(book.getId())){
            System.out.println("Book with this id already exists");
        }else{
            books.put(book.getId(), book);
        }
    }

    // Read
    public boolean containsBook(int id){
        return books.containsKey(id);
    }

    public void getList(){
        if(books.isEmpty()){
            System.out.println("List is empty");
        }else{
            for(Book book : books.values()){
                System.out.println("Id: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor());
            }
        }
    }


    // Update
    public void updateBook(int oldBookId, Book newBook){
        books.replace(oldBookId, newBook);
    }

    // Delete
    public void removeBook(int id){
        books.remove(id);
    }

    // map, filter, reduce
    public void getAllTitles(){
       books.values().stream()
               .map(book -> book.getTitle())
               .forEach(System.out :: println);
    }

    public void getBooksWithIDMoreThan5(){
        books.values().stream()
                .filter(book -> book.getId() > 5)
                .forEach(book -> System.out.println(
                 "Id: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor()
                ));
    }

    public int getAmountOfBooks(){
        return books.values().stream()
                .map(book -> 1)
                .reduce(0, (sum, one) -> sum + one);
    }
}

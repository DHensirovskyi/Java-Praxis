package CRUD;

public class Main {
    public static void main(String[]args){
        BookManager books = new BookManager();

        Book[] bookArray = {
                new Book(1, "Heart Stone", "Viktor Harkavenko"),
                new Book(2, "Java Basics", "John Doe"),
                new Book(3, "OOP Concepts", "Jane Smith"),
                new Book(4, "Functional Programming", "Alice Brown"),
                new Book(5, "Data Structures", "Bob Johnson"),
                new Book(6, "Algorithms in Depth", "Charlie White"),
                new Book(7, "Design Patterns", "Diana Green"),
                new Book(8, "Clean Code", "Robert Martin"),
                new Book(9, "Effective Java", "Joshua Bloch"),
                new Book(10, "Spring Boot Guide", "Emily Davis")
        };

        for(Book book : bookArray){
            books.addBook(book);
        }

        books.updateBook(6,new Book(4, "Soul Prod", "Dmytro Bilevych"));
        books.removeBook(4);
        books.addBook(new Book(4, "Atomic Habits", "Nestor Litopisec"));

        books.getBooksWithIDMoreThan5();
        System.out.println(books.getAmountOfBooks());

        books.getList();
    }
}

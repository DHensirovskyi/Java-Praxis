import Abstraktion.Circle;
import Abstraktion.Rectangle;
import Datenkapselung.BankAccount;
import Polymorphie.Animal;
import Polymorphie.Cat;
import Polymorphie.Cow;
import Polymorphie.Dog;
import Vererbung.Student;
import Vererbung.Teacher;

public class Main {
    public static void main(String[] args) {
        //Abstraktion
        Circle circle = new Circle(10);
        Rectangle rectangle = new Rectangle(10, 20);
        System.out.println(circle.area());
        System.out.println(rectangle.area());

        //Vererbung
        Student student = new Student("Dmytro", (byte)20, "User");
        Teacher teacher = new Teacher("Oleksandr", (byte)52, "Admin");
        student.introduce();
        teacher.introduce();

        //Datenkapselung
        BankAccount acc = new BankAccount(330.00, 192);
        acc.withdraw(30);
        acc.deposit(100);
        acc.getBalance();

        //Polymorphie
        Cat cat = new Cat();
        Dog dog = new Dog();
        Cow cow = new Cow();

        Animal[] animals = {cat, dog, cow};
        for(Animal animal : animals){
            animal.makeSound();
        }

        Person person = new Person("Dima", 16, 190);
        Robot bot = new Robot();

        person.Speak();
        bot.Speak();
    }
}

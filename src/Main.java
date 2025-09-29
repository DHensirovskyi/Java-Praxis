public class Main {
    public static void main(String[] args) {
        Person Dima = new Person("Dima", 20, 180.0f );

        Dima.sayHello();
        System.out.println(Dima.name);
        Dima.calculateYearOfBirth();
        System.out.println(Dima.toString());
    }
}

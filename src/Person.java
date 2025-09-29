public class Person {
    public String name;
    public int age;
    public float height;

    Person(String name, int age, float height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public void calculateYearOfBirth() {
        System.out.println("Was born in " + (2025 - this.age));
    }

    public void sayHello(){
        System.out.println("Hello");
    }
}
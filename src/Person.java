public class Person implements Rules {
    public String name;
    public int age;
    public float height;

    public Person(String name, int age, float height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public void calculateYearOfBirth() {
        int currentYear = java.time.Year.now().getValue();
        System.out.println("Was born in " + (currentYear - this.age));
    }

    public void sayHello() {
        System.out.println("Hello");
    }

    public void Speak(){
        System.out.println("Ich bin Mensch");
    }

    @Override
    public String toString() {
        return this.name + " is " + this.age + " years old.";
    }
}

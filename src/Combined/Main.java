package Combined;

public class Main {
    public static void main(String[] args){
        Student Dmytro = new Student("Dmytro", (byte)20, "student", "Ausbildung K&K", 1);
        Teacher Henry = new Teacher("Henry", (byte)49, "teacher", "Informatik", 34);
        Admin Tom = new Admin("Tom", (byte)30, "admin", "K&K");

        Person[] people= {Dmytro, Henry, Tom};

        for(Person human : people){
            human.introduce();
        }
    }
}

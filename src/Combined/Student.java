package Combined;

public class Student extends Person {
    private final String university;
    private final int course;

    public Student(String name, byte age, String status, String university, int course){
        this.name = name;
        this.age = age;
        this.status = status;
        this.university = university;
        this.course = course;
    }

    public void getUniversity(){
        if(age > 14){
            System.out.println(university);
        }
    }

    public void getCourse(){
        if(course >= 1){
            System.out.println(course);
        }
    }

    @Override
    public void introduce(){
        System.out.println("Hallo, Ich heiße  " + name + ", ich bin " + age + " jahre alt" + ", bin jetz Student");
    }
}

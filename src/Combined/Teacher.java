package Combined;

public class Teacher extends Person{
    private String subject;
    private int experience;

    public Teacher(String name, byte age, String status, String subject, int experience){
        this.name = name;
        this.age = age;
        this.status = status;
        this.subject = subject;
        this.experience = experience;
    }

    public void getSubject(){
        if(age > 14){
            System.out.println(subject);
        }
    }

    public void getExperience(){
        if(experience >= 1){
            System.out.println(experience);
        }
    }


    @Override
    public void introduce(){
        System.out.println("Hallo, Ich heiße  " + name + ", ich bin " + age + " jahre alt" + ", bin jetz Lehrer");
    }
}

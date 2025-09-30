package Combined;

public class Admin extends Person{
    private String department;

    public Admin(String name, byte age, String status, String department){
        this.name = name;
        this.age = age;
        this.status = status;
        this.department = department;
    }

    public void getDepartment(){
        if(age > 14){
            System.out.println(department);
        }
    }

    @Override
    public void introduce(){
        System.out.println("Hallo, Ich heiße  " + name + ", ich bin " + age + " jahre alt" + ", bin jetzt Admin");
    }
}

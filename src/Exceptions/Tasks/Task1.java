package Exceptions.Tasks;

import java.util.Scanner;

public class Task1 {

    public Task1(){}

    public void execute(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        int x = scanner.nextInt();

        System.out.print("Enter second number: ");
        int y = scanner.nextInt();

        try {
            int result = x/y;
            if (y == 0){
                throw new ArithmeticException();
            }
            System.out.println(result);
        }
        catch(ArithmeticException e){
            System.out.println("Second number can not be 0");
        }
        finally {
            System.out.println("Operation completed");
        }
    }

}

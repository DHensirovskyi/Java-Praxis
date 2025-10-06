package Exceptions.Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.OperatingSystemMXBean;
import java.util.Scanner;

public class Main {
    public static void readFile(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            System.out.println(line);
        }

        scanner.close();
    }

    public static boolean checkAge(int age){
        return age >= 18;
    }

    public static void main(String[] args){
        // Task 1
        Task1 task1 = new Task1();
        //task1.execute();


        // Task 2
        try {
            readFile("example.txt");
        }catch (FileNotFoundException e){
            System.out.println("File not found! Check the src: " + e.getMessage());
        }finally {
            System.out.println("Task completed");
        }


        // Task 3
        try{
            if(checkAge(14)){
                System.out.println("Nice, age is ok");
            }else{
                throw new TooYoungException();
            }
        }catch(TooYoungException e){
            System.out.println("You are too young");
        }
    }
}

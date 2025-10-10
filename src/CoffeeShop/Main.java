package CoffeeShop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static class CoffeeMachineException extends Exception{};

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<String> coffeeList = List.of("Latte", "Machiato", "Amerikano", "Flat White", "Espresso");
        List<Future<String>> results = new ArrayList<>();
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        System.out.println("Barista is making coffee...");
        for(String coffee : coffeeList){
            Callable<String> task = () ->{
                try {
                    Thread.sleep(2000);
                    if(Math.random() <= 0.2){
                        throw new CoffeeMachineException();
                    }

                    success.incrementAndGet();
                    return "✅ " + coffee + " is successfully made!";
                }catch (CoffeeMachineException e){
                    fail.incrementAndGet();
                    return "❌ Fail, " + " coffee machine is overheated";
                }
            };

            Future<String> future = executor.submit(task);
            results.add(future);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        for(Future<String> res : results){
            try{
                System.out.println(res.get());
            }catch (Exception e){
                System.out.println("loh2");
            }
        }
        System.out.println("------- Summary -------");
        System.out.println("Coffee made " + success.get());
        System.out.println("Fails " + fail.get());
    }
}

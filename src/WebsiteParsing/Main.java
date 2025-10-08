package WebsiteParsing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    static class TimeoutException extends Exception {}
    static class SiteRuntimeException extends Exception {}

    public static void main(String[] args) throws InterruptedException {
        List<String> websites = List.of("google.com", "youtube.com", "github.com", "hensirovskyi.dev");

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> results = new ArrayList<>();

        System.out.println("Downloading started...");

        for (String site : websites) {
            Callable<String> task = () -> {
                try {
                    Thread.sleep(2000);

                    double rand = Math.random();
                    if (rand < 0.2) {
                        throw new TimeoutException();
                    } else if (rand < 0.3) {
                        throw new SiteRuntimeException();
                    }

                    return "✅ Site " + site + " was successfully downloaded";
                }
                catch (TimeoutException e) {
                    return "❌ Timeout error on " + site;
                }
                catch (SiteRuntimeException e) {
                    return "❌ Runtime error on " + site;
                }
            };

            Future<String> future = executor.submit(task);
            results.add(future);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\n------------- Download Summary -------------");
        for (Future<String> result : results) {
            try {
                System.out.println(result.get());
            } catch (ExecutionException e) {
                System.out.println("❌ Unexpected error: " + e.getCause());
            }
        }
    }
}

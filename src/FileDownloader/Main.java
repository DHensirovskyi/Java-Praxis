package FileDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static class ConnectionLostException extends Exception {}
    static class DownloadTimedOutException extends Exception {}

    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        List<String> files = List.of("file1.zip", "file2.mp4", "file3.jpg", "file4.png", "file5.mov");
        List<Thread> threads = new ArrayList<>();
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<String>> results = new ArrayList<>();

        System.out.println("Downloading started...");
        for (String file : files) {
            Callable<String> task = (() -> {
                try {
                    Thread.sleep(1000 + random.nextInt(2000));

                    double rand = Math.random();
                    if (rand <= 0.3) {
                        throw new ConnectionLostException();
                    } else if (rand <= 0.4) {
                        throw new DownloadTimedOutException();
                    }

                    success.incrementAndGet();
                    return "✅ " + file + " downloaded successfully";
                } catch (ConnectionLostException e) {
                    fail.incrementAndGet();
                    return "❌ Connection lost for " + file;
                } catch (DownloadTimedOutException e) {
                    fail.incrementAndGet();
                    return "❌ Download timed out for " + file;
                } catch (InterruptedException e) {
                    return "❌ " + e.getMessage();
                }
            });
            Future<String> future = executor.submit(task);
            results.add(future);
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        for(Future<String> res : results){
            try {
                System.out.println(res.get());
            }
            catch (ExecutionException e){
                System.out.println("❌ Unexpected error: " + e.getCause());
            }
        }

        System.out.println("\n------------- Download Summary -------------");
        System.out.println("✅ Downloaded: " + success);
        System.out.println("❌ Failed: " + fail);
    }
}
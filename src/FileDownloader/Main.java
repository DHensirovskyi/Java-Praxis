package FileDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

        for (String file : files) {
            executor.submit(() -> {
                try {
                    Thread.sleep(1000 + random.nextInt(2000));

                    double rand = Math.random();
                    if (rand <= 0.3) {
                        throw new ConnectionLostException();
                    } else if (rand <= 0.4) {
                        throw new DownloadTimedOutException();
                    }

                    success.incrementAndGet();
                    System.out.println("✅ " + file + " downloaded successfully");
                } catch (ConnectionLostException e) {
                    System.out.println("❌ Connection lost for " + file);
                    fail.incrementAndGet();
                } catch (DownloadTimedOutException e) {
                    System.out.println("❌ Download timed out for " + file);
                    fail.incrementAndGet();
                } catch (InterruptedException e) {
                    System.out.println("❌ " + e.getMessage());
                }
            });
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("\n------------- Download Summary -------------");
        System.out.println("✅ Downloaded: " + success);
        System.out.println("❌ Failed: " + fail);
    }
}
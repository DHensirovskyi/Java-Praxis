package FileDownloader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static class ConnectionLostException extends Exception {
        public ConnectionLostException() {
        }
    }

    static class DownloadTimedOutException extends Exception {
        public DownloadTimedOutException() {
        }
    }

    public static void main(String[] args) {
        Random random = new Random();
        List<String> files = List.of("file1.zip", "file2.mp4", "file3.jpg", "file4.png", "file5.mov");
        List<Thread> threads = new ArrayList<>();
        AtomicInteger success = new AtomicInteger(0);
        AtomicInteger fail = new AtomicInteger(0);

        for (String file : files) {
            Thread thread = new Thread(() -> {
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

            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n------------- Download Summary -------------");
        System.out.println("✅ Downloaded: " + success);
        System.out.println("❌ Failed: " + fail);
    }
}
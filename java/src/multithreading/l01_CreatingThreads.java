package multithreading;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demonstrates three ways to create and start threads in Java:
 * 1. Extend Thread class and override run() - then call start() on the instance.
 * 2. Implement Runnable interface and pass it to a Thread constructor - then call start().
 * 3. Use lambda expression (modern Runnable syntax) - pass to Thread constructor and call start().
 * <p>
 * Each approach runs code concurrently in separate threads alongside the main thread.
 */

// Approach #1: Extend Thread class
class EmailThread extends Thread {
    private final String email;

    // Constructor stores the email address to be used by the thread
    public EmailThread(String email) {
        this.email = email;
    }

    // Override run() method - code here executes in a separate thread when start() is called
    @Override
    public void run() {
        System.out.println("Sending email to " + email + ": " + Thread.currentThread().getName());
    }
}

// Approach #2: Implement Runnable interface
class SmsTask implements Runnable {
    private final String number;

    // Constructor stores the phone number to be used by the thread
    public SmsTask(String number) {
        this.number = number;
    }

    // Implement run() method - code here executes in a separate thread when Thread.start() is called
    @Override
    public void run() {
        System.out.println("Sending sms to " + number + ": " + Thread.currentThread().getName());
    }
}


public class l01_CreatingThreads {
    public static void main(String[] args) throws InterruptedException {
        // Print message indicating main thread has started
        System.out.println("Main Thread started: " + Thread.currentThread().getName());

        // === Approach #1: Using Thread subclass ===
        // Create an EmailThread instance (subclass of Thread)
        EmailThread emailThread = new EmailThread("test@test.com");
        // start() launches the thread asynchronously; run() executes in the new thread
        emailThread.start();

        // === Approach #2: Using Runnable with Thread constructor ===
        // Create a SmsTask instance (implements Runnable)
        SmsTask smsTask = new SmsTask("9999989898");
        // Pass Runnable to Thread constructor; "sms-thread" is the thread's name
        Thread smsThread = new Thread(smsTask, "sms-thread");
        // start() launches this thread asynchronously
        smsThread.start();

        // === Approach #3: Using lambda expression (modern syntax) ===
        String name = "Ana";
        // Create a Thread with a lambda (functional interface); "greet-thread" is the thread's name
        Thread greetingThread = new Thread(() ->
                System.out.println("Greeting customer: " + name + " - " + Thread.currentThread().getName()), "greet-thread");
        // start() launches this thread asynchronously
        greetingThread.start();

        // Main thread continues and prints this immediately (doesn't wait for worker threads)
        System.out.println("Main Thread ended: " + Thread.currentThread().getName());

        // === Bonus: Java 21+ Virtual Thread API ===
        // Thread.ofPlatform() creates a platform thread builder
        // .daemon(false) makes this a non-daemon thread (won't allow JVM to exit if running)
        // .unstarted() creates the thread without starting it yet
        // The lambda defines the task to execute
        Thread printerThread = Thread.ofPlatform()
                .daemon(false)
                .unstarted(() -> System.out.println("printing.. - " + Thread.currentThread().getName()));
        // start() launches this thread asynchronously
        printerThread.start();


        // === Approach #4: Using ThreadFactory ===
        // ThreadFactory is a design pattern that standardizes thread creation across an application.
        // Instead of manually creating threads with new Thread(), you create a factory that handles it.

        // AtomicInteger is thread-safe counter to generate unique thread names
        AtomicInteger counter = new AtomicInteger(1);

        // Create a ThreadFactory using the builder pattern:
        // - Thread.ofPlatform() creates a platform thread builder
        // - .name("worked-", counter.getAndIncrement()) sets the thread name prefix and auto-incrementing counter
        // - .daemon(false) makes threads non-daemon (won't allow JVM to exit while running)
        // - .factory() returns a ThreadFactory instance that will create threads with these configurations
        ThreadFactory threadFactory = Thread.ofPlatform()
                .name("worked-", counter.getAndIncrement())
                .daemon(false)
                .factory();

        // Use the ThreadFactory to create and start multiple threads in a loop
        // This is cleaner than repeatedly using new Thread() with the same configurations
        for (int i = 1; i <= 10; i++) {
            // threadFactory.newThread(Runnable) creates a new Thread with the configured settings
            // Each thread gets a unique name: "worked-1", "worked-2", "worked-3", etc.
            Thread thread = threadFactory.newThread(() -> System.out.println("On - " + Thread.currentThread().getName()));
            // start() launches the thread asynchronously
            thread.start();
        }
    }
}

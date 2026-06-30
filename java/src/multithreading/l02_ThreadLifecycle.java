package multithreading;

/**
 * Java Thread States (Thread.State enum):
 * 
 * NEW - The thread object exists but start() hasn't been called yet.
 * RUNNABLE - The thread is either running on CPU or ready to run, waiting for the scheduler.
 * BLOCKED - The thread is waiting to acquire a monitor lock (synchronized block/method).
 * WAITING - The thread is waiting indefinitely for another thread to notify it (Object.wait(), Thread.join(), LockSupport.park()).
 * TIMED_WAITING - The thread is waiting for a bounded/specific amount of time (Thread.sleep(), Object.wait(long), Thread.join(long)).
 * TERMINATED - The thread has finished its run() method (normally or due to uncaught exception).
 */

// Shared resource for demonstrating BLOCKED state
class SharedResource {
    // Synchronized method creates contention for monitor lock
    synchronized void criticalSection(String threadName) {
        System.out.println(threadName + " entered critical section at " + System.currentTimeMillis());
        try {
            Thread.sleep(2000); // Hold the lock for 2 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(threadName + " exiting critical section at " + System.currentTimeMillis());
    }
}

// Shared object for demonstrating WAITING state
class WaitNotifyDemo {
    private boolean isReady = false;

    // Method that waits indefinitely for notification
    synchronized void waitForSignal(String threadName) throws InterruptedException {
        System.out.println(threadName + " is about to wait...");
        while (!isReady) {
            // Thread enters WAITING state, releases the lock, and waits for notify()
            this.wait();
        }
        System.out.println(threadName + " was notified and resumed!");
    }

    // Method that notifies waiting threads
    synchronized void sendSignal(String threadName) throws InterruptedException {
        Thread.sleep(3000); // Simulate some work
        System.out.println(threadName + " is sending signal to waiting threads");
        isReady = true;
        this.notifyAll(); // Wake up all waiting threads
    }
}

public class l02_ThreadLifecycle {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Demonstrating All Java Thread States ===\n");

        // ===== STATE 1: NEW =====
        System.out.println("1. NEW STATE:");
        Thread newThread = new Thread(() -> System.out.println("Running in new thread"));
        System.out.println("Thread created but not started - State: " + newThread.getState()); // NEW
        System.out.println();

        // ===== STATE 2: RUNNABLE =====
        System.out.println("2. RUNNABLE STATE:");
        Thread runnableThread = new Thread(() -> {
            System.out.println("Running in RUNNABLE state - State: " + Thread.currentThread().getState());
        });
        runnableThread.start(); // Transitions to RUNNABLE
        System.out.println("After start() called - State: " + runnableThread.getState()); // Usually RUNNABLE or TERMINATED
        runnableThread.join(); // Wait for it to complete
        System.out.println();

        // ===== STATE 3: BLOCKED =====
        System.out.println("3. BLOCKED STATE:");
        System.out.println("Thread waiting to acquire a synchronized lock enters BLOCKED state");
        SharedResource resource = new SharedResource();

        // Thread 1: Acquires lock and holds it
        Thread blockedThread1 = new Thread(() -> {
            resource.criticalSection("Thread-1");
        }, "Thread-1");

        // Thread 2: Will be blocked waiting for the lock
        Thread blockedThread2 = new Thread(() -> {
            System.out.println("Thread-2 attempting to enter critical section...");
            resource.criticalSection("Thread-2");
        }, "Thread-2");

        blockedThread1.start();
        Thread.sleep(500); // Give Thread-1 time to acquire lock
        
        blockedThread2.start();
        Thread.sleep(100); // Give Thread-2 time to get blocked
        System.out.println("Thread-2 State (waiting for lock): " + blockedThread2.getState()); // BLOCKED

        blockedThread1.join();
        blockedThread2.join();
        System.out.println();

        // ===== STATE 4: WAITING =====
        System.out.println("4. WAITING STATE:");
        System.out.println("Thread waiting indefinitely for another thread enters WAITING state");
        WaitNotifyDemo waitDemo = new WaitNotifyDemo();

        // Thread that waits
        Thread waitingThread = new Thread(() -> {
            try {
                waitDemo.waitForSignal("Waiter-Thread");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Waiter-Thread");

        // Thread that notifies
        Thread notifyThread = new Thread(() -> {
            try {
                waitDemo.sendSignal("Notifier-Thread");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Notifier-Thread");

        waitingThread.start();
        Thread.sleep(500); // Give waiting thread time to enter wait()
        System.out.println("Waiting Thread State: " + waitingThread.getState()); // WAITING

        notifyThread.start();
        waitingThread.join();
        notifyThread.join();
        System.out.println();

        // ===== STATE 5: TIMED_WAITING =====
        System.out.println("5. TIMED_WAITING STATE:");
        System.out.println("Thread sleeping or waiting with timeout enters TIMED_WAITING state");

        Thread timedWaitThread = new Thread(() -> {
            try {
                System.out.println("Thread going to sleep for 3 seconds...");
                Thread.sleep(3000); // Enters TIMED_WAITING state
                System.out.println("Thread woke up from sleep");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "Timed-Wait-Thread");

        timedWaitThread.start();
        Thread.sleep(500); // Give thread time to enter sleep
        System.out.println("Timed-Wait Thread State (sleeping): " + timedWaitThread.getState()); // TIMED_WAITING

        timedWaitThread.join();
        System.out.println();

        // ===== STATE 6: TERMINATED =====
        System.out.println("6. TERMINATED STATE:");
        Thread terminatedThread = new Thread(() -> {
            System.out.println("Doing some work...");
        }, "Terminated-Thread");

        terminatedThread.start();
        terminatedThread.join(); // Wait for completion
        System.out.println("Thread State (after execution completes): " + terminatedThread.getState()); // TERMINATED
        System.out.println();

        System.out.println("=== All Thread States Demonstrated ===");
    }
}

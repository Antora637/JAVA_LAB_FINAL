
class MyThread extends Thread {
    volatile boolean stopped = false;

    MyThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            if (stopped) {
                System.out.println(getName() + " stopped.");
                return;
            }

            System.out.println(getName() + " running: " + i);

            if (i == 3) {
                Thread.yield();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}

public class lab7threadMethods {
    public static void main(String[] args) throws InterruptedException {
        MyThread t1 = new MyThread("Thread-1");
        MyThread t2 = new MyThread("Thread-2");

        t1.start();
        t2.start();

        Thread.sleep(2000);

        t1.stopped = true;
        t2.stopped = true;
    }
}

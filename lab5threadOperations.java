
public class lab5threadOperations extends Thread {
    boolean suspended = false;
    boolean stopped = false;

    public void run() {
        for (int i = 1; i <= 10; i++) {
            synchronized (this) {
                while (suspended && !stopped) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }

                if (stopped)
                    return;
            }

            System.out.println("Thread running: " + i);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    synchronized void suspendThread() {
        suspended = true;
    }

    synchronized void resumeThread() {
        suspended = false;
        notifyAll();
    }

    synchronized void stopThread() {
        stopped = true;
        notifyAll();
    }

    public static void main(String[] args) throws Exception {
        lab5threadOperations t = new lab5threadOperations();

        t.start();

        Thread.sleep(1500);
        t.suspendThread();
        System.out.println("Thread Suspended");

        Thread.sleep(1500);
        t.resumeThread();
        System.out.println("Thread Resumed");

        Thread.sleep(1500);
        t.stopThread();
        System.out.println("Thread Stopped");
    }
}

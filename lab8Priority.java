
public class lab8Priority {
    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread-A: " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Thread-B: " + i);
            }
        });

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        System.out.println("Thread-A priority: " + t1.getPriority());
        System.out.println("Thread-B priority: " + t2.getPriority());

        t1.start();
        t2.start();
    }
}

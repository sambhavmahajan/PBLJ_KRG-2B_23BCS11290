class NumThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SqNumThread extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square of " + i + ": " + (i * i));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class MultiThreadExample {
    public static void main(String[] args) {
        Thread t1 = new NumThread();
        Thread t2 = new SqNumThread();

        t1.start();
        t2.start();
    }
}

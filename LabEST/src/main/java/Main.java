class Locker {}
class Even extends Thread {
    public Locker locker;
    @Override
    public void run() {
        for(int i = 0; i < 15; i += 2) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) { }
            synchronized (locker) {
                System.out.print(i + " ");
            }
        }
    }
}
class Odd extends Thread {
    public Locker locker;
    @Override
    public void run() {
        for(int i = 1; i <= 15; i += 2) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) { }
            synchronized (locker) {
                System.out.print(i + " ");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Even even = new Even();
        Odd odd = new Odd();
        Locker locker = new Locker();
        even.locker = locker;
        odd.locker = locker;
        even.start();
        Thread.sleep(100);
        odd.start();
    }
}

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 0;
        while(true) {
            try {
                t = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a number");
                sc.next();
                continue;
            }
            System.out.println("You typed " + t);
        }
    }
}

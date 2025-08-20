import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Laptop laptop = new Laptop();
        int command = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Command:" +
                "\n0: Duck Duck Go Search"+
                "\n1: take photo" +
                "\n2: Open File" +
                "\n3: Connect to internet!\n");
        String buffer;
        while(true) {
            System.out.print("Command: ");
            try {
                command = sc.nextInt();
                if(command == -1) {
                    System.out.println("Exiting the awsome laptop!");
                    sc.close();
                    return;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid integer command\n");
                sc.nextLine();
                continue;
            }
            sc.nextLine();
            switch (command) {
                case 0:
                    System.out.println("Duck Duck Go Search: ");
                    buffer = sc.nextLine();
                    System.out.println(laptop.searchOnDuckDuckGo(buffer));
                    break;
                case 1:
                        System.out.println(laptop.takePhoto());
                        break;
                case 2:
                    System.out.println("Filename: ");
                    buffer = sc.nextLine();
                    System.out.println(laptop.openFile(buffer));
                    break;
                case 3:
                    System.out.println(laptop.connectToInternet());
                    break;
                case 4:
                    System.out.println("Please enter a valid command!\n");
            }
        }
    }
}

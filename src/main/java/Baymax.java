import java.util.Objects;
import java.util.Scanner;

public class Baymax {

    public static void readInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String currInput = sc.nextLine();
            if (Objects.equals(currInput, "bye")) {
                break;
            }

            //Echoes the read Input
            System.out.println(Print.echo(currInput));
        }
    }

    public static void main(String[] args) {

        System.out.println(Print.openingMessage());

        Baymax.readInput();

        System.out.println(Print.closingMessage());
    }
}

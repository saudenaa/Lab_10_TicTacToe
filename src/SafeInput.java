import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int result;
        do {
            System.out.print(prompt + " (" + low + " - " + high + "): ");
            while (!console.hasNextInt()) {
                System.out.print("Invalid input. " + prompt + " (" + low + " - " + high + "): ");
                console.next();
            }
            result = console.nextInt();
        } while (result < low || result > high);
        return result;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        String response;
        do {
            System.out.print(prompt + " (Y/N): ");
            response = console.next().toUpperCase();
        } while (!response.equals("Y") && !response.equals("N"));
        return response.equals("Y");
    }
}

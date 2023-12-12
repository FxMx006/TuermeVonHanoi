import java.io.IOException;
import java.util.Stack;
import java.util.Scanner;

public class TürmeVonHanoi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        clearScreen();
        System.out.println("Willkommen bei Türme von Hanoi");

        // Stäbe
        int numStaebe = 3;
        int numScheiben = 2;

        // Stäbe in das Stack packen
        Stack<Integer>[] staebe = new Stack[numStaebe];
        for (int i = 0; i < numStaebe; i++) {
            staebe[i] = new Stack<>();
        }

        // Die Scheiben starten alle bei A
        for (int i = numScheiben; i > 0; i--) {
            staebe[0].push(i);
        }

        while (!isGameFinished(staebe, numScheiben)) {
            printGameStatus(staebe);

            System.out.print("Von Stab (A/B/C): ");
            char vonStab = scanner.next().charAt(0);
            System.out.print("Nach Stab (A/B/C): ");
            char nachStab = scanner.next().charAt(0);

            zugAusfuehren(staebe, vonStab - 'A', nachStab - 'A');
            clearScreen();
        }

        // Wird ausgeführt, wenn das Spiel vorbei ist.
        printGameStatus(staebe);
        System.out.println("Das Spiel ist vorbei, du hast gewonnen");
    }

    private static void zugAusfuehren(Stack<Integer>[] staebe, int vonStab, int nachStab) {
        if (!staebe[vonStab].isEmpty() && (staebe[nachStab].isEmpty() || staebe[nachStab].peek() > staebe[vonStab].peek())) {
            int scheibe = staebe[vonStab].pop();
            staebe[nachStab].push(scheibe);
        } else {
            System.out.println("Ungültiger Zug");
        }
    }

    private static void printGameStatus(Stack<Integer>[] staebe) {
        System.out.println("Aktueller Spielstand:");
        for (int i = staebe.length - 1; i >= 0; i--) {
            System.out.print((char) ('A' + i) + ": ");
            for (int j : staebe[i]) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println("-------------");
    }

    private static boolean isGameFinished(Stack<Integer>[] staebe, int numScheiben) {
        return staebe[staebe.length - 1].size() == numScheiben;
    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CryptogramGame {
    private static List<String> sayings = new ArrayList<>();
    private static Set<String> solvedSayings = new HashSet<>();

    public static void main(String[] args) {
        loadSayings("sayings.txt");
        Scanner scanner = new Scanner(System.in);
        int puzzlesSolved = 0;

        while (true) {
            String saying = getRandomSaying();
            if (saying == null) {
                System.out.println("You have solved all puzzles! Total puzzles solved = " + puzzlesSolved);
                break;
            }

            Cryptogram cryptogram = new Cryptogram(saying);
            while (cryptogram.isPlaying()) {
                System.out.println(cryptogram);
                System.out.print("Enter your guess : ");
                String input = scanner.nextLine();
                if (input.length() == 3 && input.charAt(1) == '=') {
                    char cypher = input.charAt(0);
                    char letter = input.charAt(2);
                    boolean correct = cryptogram.guess(cypher, letter);
                    if (correct) {
                        System.out.println("Correct!");
                    } else {
                        System.out.println("Incorrect guess");
                    }
                } else {
                    System.out.println("Invalid input, please use 'cypher=letter'.");
                }
            }

            if (cryptogram.hasWon()) {
                System.out.println("Congratulations! You've solved the puzzle: " + saying);
                solvedSayings.add(saying);
                puzzlesSolved++;
            } else {
                System.out.println("Game over! The correct answer was: " + saying);
                break;
            }
        }
        scanner.close();
    }

    private static void loadSayings(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sayings.add(line);
            }
        } catch (IOException e) {
            System.err.println("there is an error in the file: " + e.getMessage());
        }
    }

    private static String getRandomSaying() {
        Random random = new Random();
        List<String> availableSayings = new ArrayList<>();
        for (String saying : sayings) {
            if (!solvedSayings.contains(saying)) {
                availableSayings.add(saying);
            }
        }
        if (availableSayings.isEmpty()) {
            return null;
        }
        return availableSayings.get(random.nextInt(availableSayings.size()));
    }
}

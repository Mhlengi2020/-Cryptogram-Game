import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cryptogram {
    private String saying;
    private ArrayList<Cypher> cyphers;
    private int incorrectGuesses;

    public Cryptogram(String saying) {
        this.saying = saying;
        this.cyphers = new ArrayList<>();
        this.incorrectGuesses = 3;
        createCyphers();
    }

    private void createCyphers()  {
        List<Character> usedCyphers = new ArrayList<>();
        Random random = new Random();
        for (char s = 'A'; s <= 'Z'; s++) {
            char cypher;
            do {
                cypher = (char) ('A' + random.nextInt(26));
            } while (usedCyphers.contains(cypher) || cypher == s);
            usedCyphers.add(cypher);
            cyphers.add(new Cypher(s, cypher));
        }
    }

    public boolean guess(char cypher, char letter) {
        for (Cypher c : cyphers) {
            if (c.getCypher() == cypher) {
                if (c.getLetter() == letter) {
                    c.setGeussed(true);
                    return true;
                } else {
                    incorrectGuesses--;
                    return false;
                }
            }
        }
        return false;
    }

    public boolean hasLost() {

        return incorrectGuesses <= 0;
    }

    public boolean hasWon() {

        char [] sayingCha=saying.toUpperCase().toCharArray();
        for(int i=0;i<sayingCha.length;i++){
            char cypherCharecter=sayingCha[i];
            if(Character.isLetter(cypherCharecter)){
                Cypher cypher=findCypherForLetters(cypherCharecter);
                if(cypher != null || !cypher.isGeussed()){
                    return false;

                }
            }
        }
        return true;
    }
    protected Cypher findCypherForLetters(char letter) {
        for (int i = 0; i < cyphers.size(); i++) {
            Cypher c = cyphers.get(i);
            if (c.getCypher() == letter) {
                if (c.getLetter() == letter) {
                    return c;
                }
            }
        }
        return null;
    }

    public boolean isPlaying() {
        return !hasWon() && !hasLost();
    }

    public String toGuess() {
        StringBuilder result = new StringBuilder();
        for (char ch : saying.toCharArray()) {
            if (Character.isLetter(ch)) {
                boolean found = false;
                for (Cypher c : cyphers) {
                    if (c.getLetter() == Character.toUpperCase(ch) && c.isGeussed()) {
                        result.append(c.getLetter());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    result.append('_');
                }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public String toCypher() {
        StringBuilder result = new StringBuilder();
        for (char ch : saying.toCharArray()) {
            if (Character.isLetter(ch)) {
                for (Cypher c : cyphers) {
                    if (c.getLetter() == Character.toUpperCase(ch)) {
                        result.append(c.getCypher());
                        break;
                    }
                }
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    @Override
    public String toString() {
        return String.format("%s%n%s%n number of incorrect guesses= %d ", toGuess(),toCypher(), incorrectGuesses) ;
    }
}

public class Cypher {
    public char letter;
    public char cypher;
    public boolean geussed;

    public Cypher(char letter, char cypher) {
        this.letter = letter;
        this.cypher = cypher;
        geussed=false;
    }
    @Override
    public String toString() {
        return String.format("Letters: %c/n, Cypher: %c", letter, cypher);
    }
    public char getLetter() {
        return letter;
    }
    public char getCypher() {
        return cypher;
    }
    public boolean isGeussed() {
        return geussed;
    }
    public void setGeussed(boolean geussed) {
        this.geussed = geussed;
    }
}

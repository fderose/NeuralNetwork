public class NeuralNetworkStats {
    private int matches;
    private int mismatches;

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getMismatches() {
        return mismatches;
    }

    public void setMismatches(int mismatches) {
        this.mismatches = mismatches;
    }

    public void incrementMismatches() {
        this.mismatches++;
    }

    public void incrementMatches() {
        this.matches++;
    }

    @Override
    public String toString() {
        return "Matches: " + matches + "\nMismatches: " + mismatches;
    }
}

import java.util.ArrayList;
import java.util.List;

public class NeuralNetworkStats {

    private static class MismatchData{
        private final TrainingInstance trainingInstance;
        private final double label;
        private final double activation;

        MismatchData(TrainingInstance trainingInstance, double label, double activation) {
            this.trainingInstance = trainingInstance;
            this.label = label;
            this.activation = activation;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append(trainingInstance + "\n");
            sb.append("label: " + label + "\n");
            sb.append("activation: " + activation);
            return sb.toString();
        }
    }

    private int matches;
    private int mismatches;
    private TrainingInstance currentInstance;
    private final List<MismatchData> mismatchedInstances = new ArrayList<>();

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

    public void incrementMismatches(double label, double activation) {
        this.mismatches++;
        mismatchedInstances.add(new MismatchData(currentInstance, label, activation));
    }

    public void incrementMatches() {
        this.matches++;
    }

    public void setCurrentInstance(TrainingInstance currentInstance) {
        this.currentInstance = currentInstance;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Matches: " + matches + "\nMismatches: " + mismatches + "\n");
        for (MismatchData mismatchDate : mismatchedInstances) {
            sb.append(mismatchDate.toString() + "\n\n");
        }
        return sb.toString();
    }
}

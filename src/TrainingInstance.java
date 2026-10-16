import java.util.Arrays;

class TrainingInstance {
    private double[] inputs;
    private double[] labels;

    public double[] getInputs() { return inputs; }
    public void setInputs(double[] inputs) { this.inputs = inputs; }

    public double[] getLabels() { return labels; }
    public void setLabel(double[] labels) { this.labels = labels; }

    @Override
    public String toString() {
        return "Inputs: " + Arrays.toString(inputs);
    }
}

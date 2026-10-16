public class OutputLayerNeuron extends Neuron {
    public OutputLayerNeuron(double[] weights, double learningRate) {
       super(weights, learningRate);
    }

    public void computeError(double label, NeuralNetworkStats stats) {
        derivativeOfErrorFunctionWRTNetInput = (activation - label) * (activation * (1 - activation));
        if (stats != null) {
            System.out.println("Label: " + label + ", Activation: " + activation);
            if ((label == 1.0 && activation < .5) || (label == 0.0 && activation > .5)) {
                stats.incrementMismatches(label, activation);
            } else {
                stats.incrementMatches();
            }
        }
    }
}

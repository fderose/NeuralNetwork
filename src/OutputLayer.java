import java.util.ArrayList;
import java.util.List;

public class OutputLayer extends Layer {
    final List<OutputLayerNeuron> neurons = new ArrayList<>();

    int i = 0;
    public void createNeurons() {
        for (; i < numNeurons; i++) {
            neurons.add(new OutputLayerNeuron(weights[i], learningRate));
        }
    }

    public double[] computeOutputs(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        int i = 0;
        for (OutputLayerNeuron neuron : neurons) {
            outputs[i++] = neuron.computeOutput(inputs);
        }
        return outputs;
    }

    public void computeError(double[] labels, NeuralNetworkStats stats) {
        int i = 0;
        for (OutputLayerNeuron neuron : neurons) {
            neuron.computeError(labels[i++], stats);
        }
    }

    public double[] collectBPData() {
        double[] bpData = new double[numInputs];
        for (int i = 0; i < numInputs; i++) {
            for (int j = 0; j < numNeurons; j++) {
                bpData[i] += neurons.get(j).getDerivativeOfErrorFunctionWRTNetInput() * weights[j][i];
            }
        }
        return bpData;
    }

    public void adjustWeights() {
        for (OutputLayerNeuron neuron : neurons) {
            neuron.adjustWeights();
        }
    }
}

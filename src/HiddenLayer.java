import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class HiddenLayer extends Layer {
    private final List<HiddenLayerNeuron> neurons = new ArrayList<>();

    public void createNeurons() {
        int i = 0;
        for (; i < numNeurons; i++) {
            neurons.add(new HiddenLayerNeuron(weights[i], learningRate));
        }
    }

    public double[] computeOutputs(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        int i = 0;
        for (HiddenLayerNeuron neuron : neurons) {
            outputs[i++] = neuron.computeOutput(inputs);
        }
        return outputs;
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
        for (HiddenLayerNeuron neuron : neurons) {
            neuron.adjustWeights();
        }
    }

    public void setDerivativesOfErrorFunctionWRTNetInputs(double[] bpData) {
        int i = 0;
        for (HiddenLayerNeuron neuron : neurons) {
            neuron.setDerivativeOfErrorFunctionWRTNetInput(bpData[i++]);
        }
    }
}

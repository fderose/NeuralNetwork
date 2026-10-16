import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private String name;
    protected int numInputs;
    protected int numNeurons;
    private final List<Neuron> neurons = new ArrayList<>();
    @JsonIgnore
    protected double learningRate;
    protected double[][] weights;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumInputs() {
        return numInputs;
    }

    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    public int getNumNeurons() {
        return numNeurons;
    }

    public void setNumNeurons(int numNeurons) {
        this.numNeurons = numNeurons;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public double[][] getWeights() {
        return weights;
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    int i = 0;

    public void createNeurons() {
        for (; i < numNeurons; i++) {
            neurons.add(new Neuron(weights[i], learningRate));
        }
    }

    public double[] computeOutputs(double[] inputs) {
        double[] outputs = new double[neurons.size()];
        int i = 0;
        for (Neuron neuron : neurons) {
            outputs[i++] = neuron.computeOutput(inputs);
        }
        return outputs;
    }

    public void setDerivativesOfErrorFunctionWRTNetInputs(double[] bpData) {
        int i = 0;
        for (Neuron neuron : neurons) {
            neuron.setDerivativeOfErrorFunctionWRTNetInput(bpData[i++]);
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
        for (Neuron neuron : neurons) {
            neuron.adjustWeights();
        }
    }
}
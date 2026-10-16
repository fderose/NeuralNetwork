import com.fasterxml.jackson.annotation.JsonIgnore;

public class Neuron {
    private double[] inputs;
    private final double[] weights;
    private final double learningRate;
    private double netInput;
    protected double activation;
    protected double derivativeOfErrorFunctionWRTNetInput;

    public Neuron(double[] weights, double learningRate) {
        this.weights = weights;
        this.learningRate = learningRate;
    }

    private double logisticFunction(double u) {
        return 1.0 / (1.0 + Math.pow(Math.E, -u));
    }

    public double computeOutput(double[] inputs) {
        netInput = 0.0;
        this.inputs = inputs;
        for (int i = 0; i < this.inputs.length; i++) {
            netInput += this.inputs[i] * weights[i];
        }
        activation = logisticFunction(netInput);
        return activation;
    }

    public double getDerivativeOfErrorFunctionWRTNetInput() { return derivativeOfErrorFunctionWRTNetInput;}
    public void setDerivativeOfErrorFunctionWRTNetInput(double derivativeOfErrorFunctionWRTNetInput) { this.derivativeOfErrorFunctionWRTNetInput = derivativeOfErrorFunctionWRTNetInput * activation * (1 - activation);}

    public void adjustWeights() {
        for (int i = 0; i < inputs.length; i++) {
            weights[i] = weights[i] - learningRate * derivativeOfErrorFunctionWRTNetInput * inputs[i];
        }
    }
}

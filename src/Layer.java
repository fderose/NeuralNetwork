import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Layer {
    private String name;
    protected int numInputs;
    protected int numNeurons;
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

    public abstract void createNeurons();
    public abstract double[] computeOutputs(double[] inputs);
    public abstract double[] collectBPData();
    public abstract void adjustWeights();

}


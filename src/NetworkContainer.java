import com.fasterxml.jackson.annotation.JsonProperty;

public class NetworkContainer {
    @JsonProperty("neuralNetwork")
    private NeuralNetwork neuralNetwork;

    public NeuralNetwork getNeuralNetwork() { return neuralNetwork; }
    public void setNeuralNetwork(NeuralNetwork neuralNetwork) { this.neuralNetwork = neuralNetwork; }
}
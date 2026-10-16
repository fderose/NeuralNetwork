import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String configFile = "/Users/fderoseold/IdeaProjects/NeuralNetwork/cfg/network_config.json";
        NeuralNetworkParser parser = new NeuralNetworkParser();
        NeuralNetwork neuralNetwork = parser.parse(configFile);
        neuralNetwork.train(100000);
        neuralNetwork.saveConfiguration(configFile);
        NeuralNetworkStats stats = new NeuralNetworkStats();
        neuralNetwork.run(stats);
        System.out.println(stats);
    }
}

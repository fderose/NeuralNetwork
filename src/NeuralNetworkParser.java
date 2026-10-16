import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class NeuralNetworkParser {

    public NeuralNetwork parse(String configFile) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();
        NeuralNetwork neuralNetwork = null;

        try {
            File jsonFile = new File(configFile);
            NetworkContainer container = objectMapper.readValue(jsonFile, NetworkContainer.class);
            neuralNetwork = container.getNeuralNetwork();
            double learningRate = neuralNetwork.getLearningRate();
            System.out.println("learningRate: " + learningRate + "\n");
            System.out.println("trainingData: " + neuralNetwork.getTrainingData() + "\n");
            System.out.println("HiddenLayers:");
            neuralNetwork.getHiddenLayers().forEach(layer -> {
                layer.setLearningRate(learningRate);
                System.out.println("Layer Name: " + layer.getName());
                System.out.println("Inputs: " + layer.getNumInputs() + " | Neurons: " + layer.getNumNeurons());
                System.out.println("Weight Matrix Dimensions: "
                        + layer.getWeights().length + " rows (neurons) x "
                        + layer.getWeights()[0].length + " cols (inputs)");
                System.out.println("Weights: " + Arrays.deepToString(layer.getWeights()));
                System.out.println("learningRate: " + layer.getLearningRate());
                layer.createNeurons();
                System.out.println("----------------------------------------");

            });
            OutputLayer outputLayer = neuralNetwork.getOutputLayer();
            outputLayer.setLearningRate(learningRate);
            System.out.println("Layer Name: " + outputLayer.getName());
            System.out.println("Inputs: " + outputLayer.getNumInputs() + " | Neurons: " + outputLayer.getNumNeurons());
            System.out.println("Weight Matrix Dimensions: "
                    + outputLayer.getWeights().length + " rows (neurons) x "
                    + outputLayer.getWeights()[0].length + " cols (inputs)");
            System.out.println("Weights: " + Arrays.deepToString(outputLayer.getWeights()));
            System.out.println("learningRate: " + outputLayer.getLearningRate());
            outputLayer.createNeurons();
            System.out.println("----------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return neuralNetwork;
    }
}
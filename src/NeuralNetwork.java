import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class NeuralNetwork {
    @JsonProperty("name")
    private String name;

    @JsonProperty("hiddenLayers")
    private List<HiddenLayer> hiddenLayers;

    @JsonProperty("outputLayer")
    private OutputLayer outputLayer;

    @JsonProperty("learningRate")
    private double learningRate;

    @JsonProperty("trainingData")
    private String trainingData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<HiddenLayer> getHiddenLayers() {
        return hiddenLayers;
    }

    public void setHiddenLayers(List<HiddenLayer> hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public OutputLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(OutputLayer outputLayer) {
        this.outputLayer = outputLayer;
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public String getTrainingData() {
        return trainingData;
    }

    public void setTrainingData(String trainingData) {
        this.trainingData = trainingData;
    }

    public void train(int numEpochs) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(trainingData);

        for (int epoch = 1; epoch <= numEpochs; epoch++) {
            int counter = 1;
            try (MappingIterator<TrainingInstance> iterator = objectMapper
                    .readerFor(TrainingInstance.class)
                    .readValues(jsonFile)) {

                List<HiddenLayer> reversedHiddenLayers = new ArrayList<>(hiddenLayers);
                Collections.reverse(reversedHiddenLayers);

                while (iterator.hasNext()) {
                    TrainingInstance instance = iterator.next();
                    System.out.println("Training on epoch " + epoch + " instance " + counter++);

                    double[] inputs = instance.getInputs();
                    for (HiddenLayer hiddenLayer : hiddenLayers) {
                        inputs = hiddenLayer.computeOutputs(inputs);
                    }
                    outputLayer.computeOutputs(inputs);

                    outputLayer.computeError(instance.getLabels(), null);

                    double[] bpData = outputLayer.collectBPData();
                    for (HiddenLayer hiddenLayer : reversedHiddenLayers) {
                        hiddenLayer.setDerivativesOfErrorFunctionWRTNetInputs(bpData);
                        bpData = hiddenLayer.collectBPData();
                    }

                    outputLayer.adjustWeights();
                    for (HiddenLayer hiddenLayer : reversedHiddenLayers) {
                        hiddenLayer.adjustWeights();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw e;
            }
        }
    }

    public void run(NeuralNetworkStats stats) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(trainingData);

        int counter = 1;
        try (MappingIterator<TrainingInstance> iterator = objectMapper
                .readerFor(TrainingInstance.class)
                .readValues(jsonFile)) {

            while (iterator.hasNext()) {
                TrainingInstance instance = iterator.next();
                System.out.println("Running instance " + counter++);

                List<HiddenLayer> reversedHiddenLayers = new ArrayList<>(hiddenLayers);
                Collections.reverse(reversedHiddenLayers);

                double[] inputs = instance.getInputs();
                for (HiddenLayer hiddenLayer : hiddenLayers) {
                    inputs = hiddenLayer.computeOutputs(inputs);
                }
                outputLayer.computeOutputs(inputs);

                outputLayer.computeError(instance.getLabels(), stats);

            }

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void saveConfiguration(String outputPath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Wrap the current neural network instance back into the container structure
        NetworkContainer container = new NetworkContainer();
        container.setNeuralNetwork(this);

        File file = new File(outputPath);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, container);
        System.out.println("Updated network configuration saved to: " + file.getAbsolutePath());
    }
}

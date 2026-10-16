import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrainingDataGenerator {
    public static void main(String[] args) {
        int totalInstances = 1000;
        List<TrainingInstance> dataset = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < totalInstances; i++) {
            // Generate 3 random inputs between 0.0 and 1.0
            double[] inputs = new double[] {
                    random.nextDouble(),
                    random.nextDouble(),
                    random.nextDouble()
            };

            // Generate 2 target labels (e.g., simple classification rule: if input[0] > 0.5, [1.0, 0.0], else [0.0, 1.0])
            double[] labels = new double[2];
            if (inputs[0] > 0.5) {
                labels[0] = 1.0;
                labels[1] = 0.0;
            } else {
                labels[0] = 0.0;
                labels[1] = 1.0;
            }

            TrainingInstance instance = new TrainingInstance();
            instance.setInputs(inputs);
            instance.setLabel(labels); // Matches setLabels/setLabel depending on your TrainingInstance setter name

            dataset.add(instance);
        }

        // Write to training_data.json
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("/Users/fderoseold/IdeaProjects/NeuralNetwork/data/training_data2.json");
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, dataset);
            System.out.println("Successfully generated " + totalInstances + " training instances to " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
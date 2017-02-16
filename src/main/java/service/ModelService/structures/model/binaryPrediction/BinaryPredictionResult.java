package service.ModelService.structures.model.binaryPrediction;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import service.ModelService.structures.model.PredictionResult;

/**
 * Created by Steve on 2/13/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BinaryPredictionResult implements PredictionResult {
    private float BPResultScore;
    private float BPThreshold;
    private BPModelInstance modelInstance;
}

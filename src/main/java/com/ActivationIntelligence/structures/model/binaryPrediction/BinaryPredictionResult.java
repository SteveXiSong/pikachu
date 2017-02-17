package com.ActivationIntelligence.structures.model.binaryPrediction;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.ActivationIntelligence.structures.model.PredictionResult;

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

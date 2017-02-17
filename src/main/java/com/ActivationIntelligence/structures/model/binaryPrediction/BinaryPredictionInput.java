package com.ActivationIntelligence.structures.model.binaryPrediction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.ActivationIntelligence.structures.model.PredictionInput;

import java.util.Map;

/**
 * Created by Steve on 2/16/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BinaryPredictionInput implements PredictionInput {
    private Map<String, String> variables;
}

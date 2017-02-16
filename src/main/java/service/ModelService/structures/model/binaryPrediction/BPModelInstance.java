package service.ModelService.structures.model.binaryPrediction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import service.ModelService.structures.model.ModelInstance;

/**
 * Created by Steve on 2/15/17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BPModelInstance implements ModelInstance {
    String modelName;
    String modelId;
}

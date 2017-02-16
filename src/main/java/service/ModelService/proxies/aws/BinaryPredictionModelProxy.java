package service.ModelService.proxies.aws;

import service.ModelService.common.exceptions.InvalidInputException;
import service.ModelService.proxies.aws.exceptions.ProxyException;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionInput;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionResult;

/**
 * Created by Steve on 2/16/17.
 */
public interface BinaryPredictionModelProxy {
    /**
     * This is fake proxy.
     * Remove it after adding real proxy call.
     */
    BinaryPredictionResult getFakePrediction(BinaryPredictionInput input)
        throws ProxyException, InvalidInputException;
}

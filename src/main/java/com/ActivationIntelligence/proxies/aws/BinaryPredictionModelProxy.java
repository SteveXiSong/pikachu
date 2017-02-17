package com.ActivationIntelligence.proxies.aws;

import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionInput;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionResult;

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

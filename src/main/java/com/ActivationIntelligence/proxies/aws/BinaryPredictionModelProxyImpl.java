package com.ActivationIntelligence.proxies.aws;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Configuration;
import com.ActivationIntelligence.common.exceptions.InvalidInputException;
import com.ActivationIntelligence.proxies.aws.exceptions.ProxyException;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionInput;
import com.ActivationIntelligence.structures.model.binaryPrediction.BinaryPredictionResult;

/**
 * Created by Steve on 2/16/17.
 */
@Log4j
@Configuration
public class BinaryPredictionModelProxyImpl implements BinaryPredictionModelProxy {
    private String region;

    @Override
    public BinaryPredictionResult
    getFakePrediction(@NonNull final BinaryPredictionInput input)
            throws ProxyException, InvalidInputException {
        log.info(String.format("getFakePrediction received [%s]", input.toString()));
        BinaryPredictionResult result = new BinaryPredictionResult();
        result.setBPResultScore(0.8f);
        result.setBPThreshold(0.7f);
        return result;
    }

}

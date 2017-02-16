package service.ModelService.proxies.aws;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.ModelService.common.exceptions.InvalidInputException;
import service.ModelService.proxies.aws.exceptions.ProxyException;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionInput;
import service.ModelService.structures.model.binaryPrediction.BinaryPredictionResult;

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

package services.controllers;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import structures.binPredRes;

/**
 * Created by Steve on 2/13/17.
 */
@RestController
public class binPredResController {

    private static final String template = "Prediction is %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/getPrediction")
    public binPredRes getPrediction(@RequestParam(value="modelName", defaultValue="BinaryPrediction")
            String modeName) {
        return new binPredRes(counter.incrementAndGet(), String.format(template, modeName));
    }
}

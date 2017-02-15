package service.ModelService.structures;

import lombok.Getter;
import lombok.AllArgsConstructor;

/**
 * Created by Steve on 2/13/17.
 */
@AllArgsConstructor
@Getter
public class binPredRes {
    private long score;
    private String modelName;

    /*
    public service.ModelService.structures.binPredRes(long score, final String name) {
        this.score = score;
        this.modelName = name;
    }
    */
}

package structures;

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
    public binPredRes(long score, final String name) {
        this.score = score;
        this.modelName = name;
    }
    */
}

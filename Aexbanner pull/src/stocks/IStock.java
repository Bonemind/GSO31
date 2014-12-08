package stocks;

import java.io.Serializable;

/**
 * Created by Subhi on 30-11-2014.
 */
public interface IStock extends Serializable {
    public String getName();
    public double getValue();
}

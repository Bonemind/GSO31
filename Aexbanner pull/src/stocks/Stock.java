package stocks;

/**
 * Created by Subhi on 30-11-2014.
 */
public class Stock implements IStock{
    private String name;
    private double value;
    public Stock(String name, double value) {
        this.name = name;
        this.value = value;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getValue() {
        return value;
    }
}

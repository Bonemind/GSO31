package stocks;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Subhi on 30-11-2014.
 */
public class StockExchangeMock implements IStockExchange {
    private DecimalFormat df = new DecimalFormat("#.##");
    private IStock[] stocks;

    /**
     * Default constructor
     */
   public StockExchangeMock() {
       regenerateStocks();
       Timer timer = new Timer();
       //Set a timer to regenerate the stocks every minute
       timer.schedule(new TimerTask() {
           @Override
           public void run() {
               regenerateStocks();
           }
       }, 6 * 1000, 6*1000);
   }

    /**
     * Returns the current stocks
     * @return the array of stocks
     */
    @Override
    public IStock[] getStocks() {
        return stocks;
    }

    /**
     * Regenerates the values of stocks
     */
    private void regenerateStocks() {
        Random rand = new Random();
        stocks = new IStock[10];
        for (int i = 0; i < stocks.length; i++) {
            stocks[i] = new Stock("Some Stock: ", rand.nextDouble());
        }
        System.out.println("Regenerated stocks");
    }
}

package stocks;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by Subhi on 30-11-2014.
 */
public class StockExchangeMock implements IStockExchange {
    private DecimalFormat df = new DecimalFormat("#.##");
    private IStock[] stocks;
    private ArrayList<IStockClient> clients = new ArrayList<>();

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
     * Register a client.
     *
     * @param client The client
     */
    @Override
    public void registerClient(IStockClient client) throws RemoteException {
        System.out.println("Client registered");
        try {
            client.setStocks(stocks);
            clients.add(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
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

        //Push to all clients
        for (IStockClient client : (ArrayList<IStockClient>) clients.clone()) {
            try {
                client.setStocks(stocks);
            } catch (RemoteException e) {
                e.printStackTrace();
                System.out.println("Removed client");
                clients.remove(client);
            }
        }
    }
}

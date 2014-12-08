package stocks;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.rmi.RemoteException;

/**
 * Created by Subhi on 30-11-2014.
 */
public class StockExchange implements IStockExchange{
    @Override
    public void registerClient(IStockClient client) throws RemoteException {
        throw new NotImplementedException();
    }
}

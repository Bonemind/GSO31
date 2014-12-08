package stocks;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Subhi on 30-11-2014.
 */
public interface IStockExchange extends Remote {
    public void registerClient(IStockClient client) throws RemoteException;
}

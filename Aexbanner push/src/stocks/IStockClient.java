package stocks;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Michon on 8-12-2014.
 */
public interface IStockClient extends Remote {
    public void setStocks(IStock[] stocks) throws RemoteException;
}

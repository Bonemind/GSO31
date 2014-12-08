package aexbanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import stocks.IStock;
import stocks.IStockExchange;
import stocks.StockExchange;
import stocks.StockExchangeMock;

import javax.swing.text.LabelView;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.time.Duration;

public class AEXBanner extends Application {
    /**
     * The port to use for this application
     */
    public final static int PORT = 6588;

    /**
     * Main javafx entrypoint
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            //Grab the registry
            //Lookup the stockexchange, if that succeeds we're in business and can launch the gui
            Registry registry = LocateRegistry.getRegistry(PORT);
            IStockExchange stub = (IStockExchange) registry.lookup("ex");
            System.out.println("Remote exchange found");

            //Launch the gui
            Parent root = FXMLLoader.load(getClass().getResource("AEXBanner.fxml"));
            primaryStage.setTitle("Hello World");
            Scene scene = new Scene(root, 300, 275);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (RemoteException e) {
            //We couldn't connect to the server, starting one instead
            startServer();
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Starts a server
     */
    private void startServer() {
        try {
            StockExchangeMock exchange = new StockExchangeMock();
            IStockExchange stub = (IStockExchange) UnicastRemoteObject.exportObject(exchange, 0);
            Registry registry = LocateRegistry.createRegistry(PORT);
            registry.bind("ex", stub);
            System.out.println("Server started");
            Thread.sleep(60 * 1000 * 5);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

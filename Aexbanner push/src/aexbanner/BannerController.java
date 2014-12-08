package aexbanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import stocks.IStock;
import stocks.IStockClient;
import stocks.IStockExchange;
import stocks.StockExchangeMock;

import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class BannerController implements IStockClient {

    /**
     * The animationtimer to use to animate the labels
     */
    private AnimationTimer animationTimer;

    /**
     * The pane that will contain the stocks
     */
    private Pane stocksPane;
    @FXML private GridPane page;

    /**
     * Initialization
     */
    public void initialize() {
        System.out.println("Init");
        stocksPane = new Pane();
        page.add(stocksPane, 0, 0);
        animationTimer = new AnimationTimer() {

            /**
             * This method needs to be overridden by extending classes. It is going to
             * be called in every frame while the {@code AnimationTimer} is active.
             *
             * @param now The timestamp of the current frame given in nanoseconds. This
             *            value will be the same for all {@code AnimationTimers} called
             *            during one frame.
             */
            @Override
            public void handle(long now) {
                animateTicker();
            }
        };

        // Register with the stock exchange.
        IStockExchange stockExchange;
        try {
            Registry reg = LocateRegistry.getRegistry(AEXBanner.PORT);

            // Register this client.
            IStockClient stub = (IStockClient) UnicastRemoteObject.exportObject(this, 0);
            stockExchange = (IStockExchange) reg.lookup("ex");
            stockExchange.registerClient(stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles animating the ticker by making each stock label move to the end
     * and jumping to the en of the queue when it becomes invisible
     */
    private void animateTicker() {
        double lastWidth = -999;
        double lastX = 0;
        boolean firstLoop = true;
        for (Node c : stocksPane.getChildren()) {
            if (c instanceof Label) {
                if (firstLoop) {
                    c.setTranslateX(c.getTranslateX() - 1);
                    lastX = c.getTranslateX();
                    lastWidth = ((Label) c).getWidth();
                    firstLoop = false;
                } else {
                    c.setTranslateX(lastX + lastWidth);
                    lastWidth = ((Label) c).getWidth();
                    lastX = c.getTranslateX();
                }
            }
        }

        if (((Label) stocksPane.getChildren().get(0)).getTranslateX() < -1 * ((Label) stocksPane.getChildren().get(0)).getWidth()) {
            Node n = stocksPane.getChildren().get(0);
            stocksPane.getChildren().remove(0);
            n.setTranslateX(500);
            stocksPane.getChildren().add(n);
        }
    }

    /**
     * Receive the stocks.
     *
     * @param stocks
     * @throws RemoteException
     */
    @Override
    public void setStocks(IStock[] stocks) throws RemoteException {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                animationTimer.stop();
                stocksPane.getChildren().clear();
                DecimalFormat df = new DecimalFormat("#.##");
                for (IStock stock : stocks) {
                    Label lbl = new Label(stock.getName() + ": " + df.format(stock.getValue()));
                    stocksPane.getChildren().add(lbl);
                }
                animationTimer.start();
                System.out.println("Stocks refreshed");
            }
        });
    }
}


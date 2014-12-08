package aexbanner;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import stocks.IStock;
import stocks.IStockExchange;
import stocks.StockExchangeMock;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    /**
     * The animationtimer to use to animate the labels
     */
    private AnimationTimer animationTimer;

    /**
     * The stockexchange object to use
     */
    private IStockExchange stockExchange;

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
        try {
            stockExchange = (IStockExchange) LocateRegistry.getRegistry(AEXBanner.PORT).lookup("ex");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
       // stockExchange = new StockExchangeMock();
        refreshStocks();

        //Schedules a timer to pull the new stocks every minute
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        refreshStocks();
                    }
                });
            }
        }, 6 * 1000, 6*1000);
    }

    /**
     * Pulls the new stocks from the remote stockexchange
     */
    private void refreshStocks() {
        animationTimer.stop();
        stocksPane.getChildren().clear();
        IStock[] stocks = new IStock[0];
        try {
            stocks = stockExchange.getStocks();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        for (IStock stock : stocks) {
            Label lbl = new Label(stock.getName() + ": " + df.format(stock.getValue()));
            stocksPane.getChildren().add(lbl);
        }
        animationTimer.start();
        System.out.println("Stocks refreshed");
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

}

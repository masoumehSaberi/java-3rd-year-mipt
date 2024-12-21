import java.util.concurrent.*;
import java.util.*;

interface Taxi {
    void placeOrder(String order);
}

interface Dispatcher {
    void notifyAvailable(TaxiDriver driver);
}

class TaxiDriver implements Taxi, Runnable {
    private final String name;
    private Dispatcher dispatcher;
    private BlockingQueue<String> orders = new LinkedBlockingQueue<>();
    private Random random = new Random();

    public TaxiDriver(String name, Dispatcher dispatcher) {
        this.name = name;
        this.dispatcher = dispatcher;
    }

    @Override
    public void placeOrder(String order) {
        try {
            orders.put(order);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                String order = orders.take();
                System.out.println(name + " is fulfilling order: " + order);
                Thread.sleep(random.nextInt(2000) + 1000);
                System.out.println(name + " completed order: " + order);
                dispatcher.notifyAvailable(this);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class TaxiDispatcher implements Dispatcher, Runnable {
    private final BlockingQueue<TaxiDriver> availableTaxis = new LinkedBlockingQueue<>();
    private final Random random = new Random();

    public void registerTaxi(TaxiDriver driver) {
        availableTaxis.add(driver);
    }

    @Override
    public void notifyAvailable(TaxiDriver driver) {
        availableTaxis.add(driver);
    }

    @Override
    public void run() {
        while (true) {
            try {
                TaxiDriver driver = availableTaxis.take();
                String order = "Order #" + random.nextInt(100);
                System.out.println("Dispatcher placing " + order + " to " + driver);
                driver.placeOrder(order);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
public class TaxiApplication {
    public static void main(String[] args) {
        TaxiDispatcher dispatcher = new TaxiDispatcher();
        Thread dispatcherThread = new Thread(dispatcher);

        TaxiDriver driver1 = new TaxiDriver("Taxi1", dispatcher);
        TaxiDriver driver2 = new TaxiDriver("Taxi2", dispatcher);
        TaxiDriver driver3 = new TaxiDriver("Taxi3", dispatcher);

        dispatcher.registerTaxi(driver1);
        dispatcher.registerTaxi(driver2);
        dispatcher.registerTaxi(driver3);

        dispatcherThread.start();
        new Thread(driver1).start();
        new Thread(driver2).start();
        new Thread(driver3).start();
    }
}
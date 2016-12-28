public class RequestHandler extends Thread {
    private final Dispatcher dispatcher;
    private final int requestID;
    private final Integer resourceID;

    public RequestHandler(Dispatcher dispatcher, int requestID, Integer resourceID) {
        this.dispatcher = dispatcher;
        this.requestID = requestID;
        this.resourceID = resourceID;
    }

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Request No. " + requestID + " has been resolved by resource " + resourceID);
        dispatcher.freeResource();
    }
}

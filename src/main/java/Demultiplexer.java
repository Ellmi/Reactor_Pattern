import java.util.concurrent.*;
import static java.lang.Thread.sleep;

public class Demultiplexer {
    private ArrayBlockingQueue<Integer> resources = new ArrayBlockingQueue<Integer>(3);

    {
        resources.add(1);
        resources.add(2);
        resources.add(3);
    }

    public synchronized Integer getResource(long currentTimeMillis, int timeout) {
        while (true) {
            if (System.currentTimeMillis() - currentTimeMillis > timeout * 1000) {
                throw new RuntimeException("Get resource timeout!");
            } else if (resources.size() > 0) {
                return resources.poll();
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void freeResource(Integer resourceId) {
        resources.add(resourceId);
    }

    public void accept(int requestID) {
        long currentTimeMillis = System.currentTimeMillis();
        Integer resourceID = getResource(currentTimeMillis, 5);
    }

}

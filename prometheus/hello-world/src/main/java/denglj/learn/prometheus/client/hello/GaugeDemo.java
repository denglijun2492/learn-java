package denglj.learn.prometheus.client.hello;

import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Random;
import java.util.RandomAccess;

public class GaugeDemo {
    private static final Gauge inprogressRequests ;

    private static final Random random = new Random();

    static {
        inprogressRequests = Gauge
                .build()
                .name("hello_requests")
                .help("Inprogress requests.")
                .register();
    }

    public void hello(String message) throws InterruptedException {
        inprogressRequests.inc();
        System.out.println(String.format("hello %s ...", message));
        int i = random.ints(1000, 5000).findFirst().getAsInt();
        Thread.sleep(i);
        inprogressRequests.dec();
    }

    public static void start(){
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            new GaugeDemo().hello("denglj");
                            int i = random.ints(1000, 5000).findFirst().getAsInt();
                            Thread.sleep(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(1234);
        start();
    }
}

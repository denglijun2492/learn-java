package denglj.learn.prometheus.client.hello;

import io.prometheus.client.Histogram;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Random;

public class HistogramDemo {

    private static final Random random = new Random();

    public static final Histogram requestLatency;

    static {
        requestLatency = Histogram
                .build()
                .labelNames("a", "b")
                .name("hello2_latency_seconds")
                .help("Request latency in seconds.")
                .register();
    }

    public void hello(String message){
        Histogram.Timer requestTimer = requestLatency.labels("1","2").startTimer();
        try {
            System.out.println(String.format("hello %s ...", message));
        }finally {
            requestTimer.observeDuration();
        }
    }

    public static void start(){
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            new HistogramDemo().hello("denglj");
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

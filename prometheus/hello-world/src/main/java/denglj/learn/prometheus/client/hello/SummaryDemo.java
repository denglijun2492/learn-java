package denglj.learn.prometheus.client.hello;

import io.prometheus.client.Summary;
import io.prometheus.client.exporter.HTTPServer;

import java.io.IOException;
import java.util.Random;

public class SummaryDemo {

    private static final Random random = new Random();
    private static final Summary receivedBytes;
    private static final Summary requestLatency ;

    static{
        receivedBytes = Summary
                .build()
                .quantile(0.5, 0.01)
                .quantile(0.9, 0.01)
                .name("hello_size_bytes")
                .help("Request size in bytes.")
                .register();
        requestLatency = Summary
                .build()
                .name("hello_latency_seconds")
                .help("Request latency in seconds.")
                .register();
    }

    public void hello(String message){
        Summary.Timer helloTimer = requestLatency.startTimer();
        try {
            System.out.println(String.format("hello %s ...", message));
        }finally {
            int i = random.ints(1000, 10000).findFirst().getAsInt();
            receivedBytes.observe(i);
            helloTimer.observeDuration();
        }
    }

    public static void start(){
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        try {
                            new SummaryDemo().hello("denglj");
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

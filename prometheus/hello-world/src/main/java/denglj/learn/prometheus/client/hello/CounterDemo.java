package denglj.learn.prometheus.client.hello;

import io.prometheus.client.Counter;
import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.StandardExports;

import java.io.IOException;
import java.util.Random;

public class CounterDemo {

    private static final Counter counter;

    static{
        counter = Counter
                .build()
                .labelNames("lab1", "lab2")
                .name("hello_total")
                .help("Total hello count.")
                .register();
        new StandardExports().register();
    }

    public void hello(String messge){
        System.out.println(String.format("hello %s ...", messge));
        counter.labels("aaa", "bbb").inc();
    }

    public static void main(String[] args) throws IOException {
        HTTPServer server = new HTTPServer(1234);
        new Thread(new Runnable() {
            public void run() {
                while (true){
                    new CounterDemo().hello("denglj.");
                    try {
                        int i = new Random().ints(10, 2000).findFirst().getAsInt();
                        Thread.sleep(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

package denglj.learn.prometheus.client;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

public class MyCustomServer {
    public void start() throws IOException {
        System.out.println("开始启动metric server..");
        DefaultExports.initialize();
        HTTPServer server = new HTTPServer(1235, true);
        System.out.println("启动成功metric server..");
    }
}

package denglj.learn.prometheus.client.collector;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;

public class DefaultExport {

    public static void main(String[] args) throws IOException {
        DefaultExports.initialize();
        HTTPServer server = new HTTPServer(1234);
    }
}

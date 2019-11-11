package denglj.learn.prometheus.client;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(urlPatterns = "/qqqqq")
public class MyCustomServlet extends HttpServlet {


    /**
     * Construct a MetricsServlet for the default registry.
     */
    public MyCustomServlet() {
        System.out.println("初始化custom servlet..............");
    }

}

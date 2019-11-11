package denglj.learn.prometheus.client;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

//@WebListener
public class PrometheusContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("初始化prometheus...............");
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletRegistration.Dynamic metricsServlet = servletContext.addServlet("metricsServlet", MetricsServlet.class);
        metricsServlet.addMapping("/metrics");
        DefaultExports.initialize();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

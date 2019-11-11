package com.dragonsoft.agent;

import io.prometheus.client.exporter.MetricsServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletRegistration;

public class PrometheusContextListener {
    public static void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("当前classLoader3:" + Thread.currentThread().getContextClassLoader());
        System.out.println("调用getServletContext........");
        ServletContext servletContext = servletContextEvent.getServletContext();
        ServletRegistration.Dynamic metricServlet = servletContext.addServlet("metricServlet", new MetricsServlet());
        metricServlet.addMapping("/metric");
    }
}

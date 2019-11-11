package com.dragonsoft.qb;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.exporter.common.TextFormat;
import io.prometheus.client.hotspot.DefaultExports;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebServlet(urlPatterns = "/abcd")
public class PrometheusServlet extends HttpServlet {

    private CollectorRegistry registry;

    /**
     * Construct a MetricsServlet for the default registry.
     */
    public PrometheusServlet() {
        this(CollectorRegistry.defaultRegistry);
        System.out.println("初始化prometheus servlet..............");
    }

    /**
     * Construct a MetricsServlet for the given registry.
     * @param registry collector registry
     */
    public PrometheusServlet(CollectorRegistry registry) {
        this.registry = registry;
        DefaultExports.initialize();
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType(TextFormat.CONTENT_TYPE_004);

        Writer writer = resp.getWriter();
        try {
            TextFormat.write004(writer, registry.filteredMetricFamilySamples(parse(req)));
            writer.flush();
        } finally {
            writer.close();
        }
    }

    private Set<String> parse(HttpServletRequest req) {
        String[] includedParam = req.getParameterValues("name[]");
        if (includedParam == null) {
            return Collections.emptySet();
        } else {
            return new HashSet<String>(Arrays.asList(includedParam));
        }
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}

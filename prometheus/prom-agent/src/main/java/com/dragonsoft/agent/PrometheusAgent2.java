package com.dragonsoft.agent;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;

import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.util.HashMap;
import java.util.Map;

public class PrometheusAgent2 {
    public static void premain(String agentArgs, Instrumentation inst) throws IOException {
        Map<String, String> params = new HashMap<>();
        if(agentArgs != null){
            String[] splits = agentArgs.trim().split(",");
            for (String s : splits) {
                String[] pair = s.split(">");
                System.out.println(String.format("参数名:%s， 值:%s", pair[0], pair[1]));
                params.put(pair[0], pair[1]);
            }
        }
        System.out.println("开始启动metric server..");
        DefaultExports.initialize();
        String port = params.containsKey("port") ? params.get("port") : "1234";
        HTTPServer server = new HTTPServer(Integer.valueOf(port));
        System.out.println("启动成功metric server..");

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                DefaultExports.initialize();
//                try {
//                    System.out.println("开始启动metric server..");
//                    HTTPServer server = new HTTPServer(1234);
//                    System.out.println("启动成功metric server..");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println(".........");
                        Thread.sleep(5000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

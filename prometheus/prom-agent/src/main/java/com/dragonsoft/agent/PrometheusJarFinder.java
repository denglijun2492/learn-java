package com.dragonsoft.agent;

import java.io.File;
import java.net.URL;

public class PrometheusJarFinder {

    public static File findPath(){
        File agentJarFile = null;
        String classResourcePath = PrometheusJarFinder.class.getName().replaceAll("\\.", "/") + ".class";
        URL resource = ClassLoader.getSystemClassLoader().getResource(classResourcePath);
        if (resource != null) {
            String urlString = resource.toString();
            int insidePathIndex = urlString.indexOf('!');
            boolean isInJar = insidePathIndex > -1;
            if (isInJar) {
                urlString = urlString.substring(urlString.indexOf("file:"), insidePathIndex);
                try {
                    agentJarFile = new File(new URL(urlString).toURI());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (agentJarFile.exists()) {
                    return agentJarFile.getParentFile();
                }
            } else {
                int prefixLength = "file:".length();
                String classLocation = urlString.substring(prefixLength, urlString.length() - classResourcePath.length());

                agentJarFile = new File(classLocation);
            }
        }

        if(agentJarFile != null){
            System.out.println("找到agent jar." + agentJarFile.getPath());
        }

        return agentJarFile;
    }
}

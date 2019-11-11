package com.dragonsoft.agent;

public class MyClassLoader extends ClassLoader{

    private ClassLoader appLoader;
    private ClassLoader webLoader;

    public MyClassLoader(ClassLoader appLoader, ClassLoader webLoader){
        System.out.println("传入appLoader: " + appLoader);
        System.out.println("传入webLoader: " + webLoader);
        this.appLoader = appLoader;
        this.webLoader = webLoader;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    c = webLoader.loadClass(name);
                } catch (ClassNotFoundException e) {
                   // c = appLoader.loadClass(name);
                }

            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }
}

package denglj.learn.execute;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by denglj on 2019/7/11.
 */
public class MyExecutorClassLoader extends URLClassLoader {

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

    private final static String baseDir = System.getProperty("user.dir") + File.separator + "ext" + File.separator;

    public MyExecutorClassLoader(String version){
        super(new URL[]{}, null); //将parent设置为Null

        loadResource(version);
    }

    private void loadResource(String version) {
        String jarPath = baseDir + version;

        // 加载对应版本目录下的 Jar 包
        tryLoadJarInDir(jarPath);
        // 加载对应版本目录下的 lib 目录下的 Jar 包
        tryLoadJarInDir(jarPath + File.separator + "lib");
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        System.out.println("my load class :"+ name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try{
            return super.findClass(name);
        }catch(ClassNotFoundException e){
            return MyExecutorClassLoader.class.getClassLoader().loadClass(name);
        }
    }

    private void tryLoadJarInDir(String dirPath) {
        File dir = new File(dirPath);
        // 自动加载目录下的jar包
        if (dir.exists() && dir.isDirectory()) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    this.addURL(file);
                     continue;
                }
            }
        }
    }

    private void addURL(File file) {
        try {
            super.addURL(new URL("file", null, file.getCanonicalPath()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

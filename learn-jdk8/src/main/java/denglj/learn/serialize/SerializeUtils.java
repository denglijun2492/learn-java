package denglj.learn.serialize;

import java.io.*;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 17:10
 **/
public class SerializeUtils {

    public static Object dserialize(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    public static void serialize(Object object, String path) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        fos.close();
    }



    public static void main(String[] args) {
    }
}

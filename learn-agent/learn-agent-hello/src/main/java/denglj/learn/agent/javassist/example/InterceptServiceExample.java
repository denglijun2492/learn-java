package denglj.learn.agent.javassist.example;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.util.HotSwapper;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/10 16:58
 **/
public class InterceptServiceExample {

    private static ClassPool pool = ClassPool.getDefault();

    public static void main(String[] args) throws Exception {

        update();

        PersonService personService = new PersonService();
        personService.printName();


    }

    public static void update() throws Exception {
        CtClass cc = pool.get("denglj.learn.agent.javassist.example.InterceptServiceExample$PersonService");
        CtMethod printName = cc.getDeclaredMethod("printName");
        printName.insertBefore("System.out.println(\"开始打印...\");");
        printName.insertAfter("System.out.println(\"结束打印...\");");
        //将修改后的CtClass加载至当前线程的上下文类加载器中，调用的是ClassPool.toClass
        cc.toClass();
    }

    public static class PersonService{
        public void printName(){
            System.out.println("this is denglj as default...");
        }
    }
}

package denglj.learn.agent.javassist.example;

import javassist.*;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/10 16:14
 **/
public class CreatePersonExample {

    private static ClassPool pool = ClassPool.getDefault();

    /**
     * 创建一个Person类
     * @return
     * @throws Exception
     */
    public static CtClass create() throws Exception {
        CtClass string = pool.get("java.lang.String");

        //1.创建一个空类
        CtClass cc = pool.makeClass("denglj.learn.javassist.example.Person");

        //2.新增一个字段 name
        CtField field1 = new CtField(string, "name", cc);
        //  设置私有
        field1.setModifiers(Modifier.PRIVATE);
        //  添加字段并初始值
        cc.addField(field1, CtField.Initializer.constant("denglj is good man..."));

        //3.生成get和set方法
        cc.addMethod(CtNewMethod.setter("setName", field1));
        cc.addMethod(CtNewMethod.getter("getName", field1));

        //4.添加无参构造函数
        CtConstructor cons = new CtConstructor(new CtClass[]{}, cc);
        cons.setBody("{name = \"denglj\";}");
        cc.addConstructor(cons);

        //5.添加有参构造函数 $0-this $1-参数1 $2-参数2...
        CtConstructor cons2= new CtConstructor(new CtClass[]{string}, cc);
        cons2.setBody("{$0.name = $1;}");
        cc.addConstructor(cons2);

        //6.创建一个名为printName方法，无参数，无返回值
        CtMethod method = new CtMethod(CtClass.voidType, "printName", new CtClass[]{}, cc);
        method.setModifiers(Modifier.PUBLIC);
        method.setBody("{System.out.println(\"this is \" + $0.name);}");
        cc.addMethod(method);

        //7.生成class文件到指定目录.
//        cc.writeFile("Q:\\git\\s1\\learn-java\\learn-agent\\learn-agent-hello\\temp");
        return cc;
    }

    /**
     * 在Person创建基础上，实现IPerson接口，调用时更友好
     * @return
     * @throws Exception
     */
    public static CtClass createByInterface() throws Exception {
        create();
        CtClass clazz1 = pool.get("denglj.learn.agent.javassist.example.CreatePersonExample$IPerson");
        CtClass clazz2 = pool.get("denglj.learn.javassist.example.Person");
        clazz2.setInterfaces(new CtClass[]{clazz1});
        return clazz2;
    }

    public static void main(String[] args) throws Exception {
        //反射调用
//        Class clazz = create().toClass();
//        Object dengsy = clazz.getConstructor(String.class).newInstance("dengsy");
//        clazz.getMethod("printName").invoke(dengsy);

        //接口调用
        Class clazz = createByInterface().toClass();
        IPerson dengsy = (IPerson)clazz.getConstructor(String.class).newInstance("dengsy");
        dengsy.printName();
    }

    public static interface IPerson {
        void printName();
    }
}

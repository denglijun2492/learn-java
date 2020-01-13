package denglj.learn.serialize;

import org.junit.Test;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 17:20
 **/
public class WriteReadTests {
    @Test
    public void write() throws Exception {
        Employee employee = new Employee();
        employee.setId("123");
        employee.setName("邓礼俊");
        employee.setSalary(10000000);
        SerializeUtils.serialize(employee, "Q:\\git\\s1\\learn-java\\learn-jdk8\\temp\\employee-123.obj");
    }

    /**
     * 如果Employee新增字段的话，出现异常
     * java.io.InvalidClassException: denglj.learn.serialize.Employee; local class incompatible: stream classdesc serialVersionUID = -6947179003382614299, local class serialVersionUID = 629469500223112226
     * 需要添加serialVersionUID
     */
    @Test
    public void read() throws Exception {
        Employee employee = (Employee)SerializeUtils.dserialize("Q:\\git\\s1\\learn-java\\learn-jdk8\\temp\\employee-123.obj");
        System.out.println(employee.toString());
    }
}

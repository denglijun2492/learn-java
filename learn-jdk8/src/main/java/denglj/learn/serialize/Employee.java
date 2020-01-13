package denglj.learn.serialize;


import java.io.Serializable;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2020/1/13 17:03
 **/
public class Employee implements Serializable {
    private static final long serialVersionUID = -6947179003382614299L;
    private String id;
    private String name;
    //transient标识不会被序列化
    private int salary;
    private String address;
    private String father;

    @Override
    public String toString() {
        return id + " - " + name + " - " + salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }
}

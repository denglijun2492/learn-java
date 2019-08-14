package denglj.learn.mybatis.entity;

import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Created by denglj on 2019/7/2.
 */
public class User {
    @TableId
    private String id;
    private String name;
    private Integer age;
    private String email;

    public User(String id, String name){
        this.id = id;
        this.name = name;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

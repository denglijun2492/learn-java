package denglj.learn.user;

import lombok.Data;

@Data
public class User {
    private String id;
    private String name;
    private Integer age;
    private String email;

    public User(){

    }
    public User(String id, String name){
        this.id = id;
        this.name = name;
    }
}

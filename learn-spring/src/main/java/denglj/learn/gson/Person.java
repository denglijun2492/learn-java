package denglj.learn.gson;

import lombok.Data;

import java.util.List;

@Data
public class Person {
    private String name;
    private String birthday;
    private String nation;
    private int age;
    private List<GsonAddress> addressList;
}

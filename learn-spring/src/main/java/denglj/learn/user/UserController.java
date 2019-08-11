package denglj.learn.user;

import denglj.learn.aop.SystemOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @SystemOperation(code = "001", desc = "查询用户列表")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        List<User> result = new ArrayList<>();
        result.add(new User("1", "denglj"));
        result.add(new User("2", "wuxp"));

        int i = 0;
        int k = 10 / i;
        return result;
    }

    @SystemOperation(code = "002", desc = "加载用户")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id){
        return new User("1", "denglj");
    }

    @SystemOperation(code = "003", desc = "新增用户")
    @RequestMapping(method = RequestMethod.POST)
    public User save(@RequestBody User user){
        user.setId("999");
        return user;
    }

    @SystemOperation(code = "004", desc = "删除用户")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") String id){
        return "1";
    }

}

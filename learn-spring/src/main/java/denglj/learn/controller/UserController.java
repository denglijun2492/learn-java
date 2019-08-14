package denglj.learn.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import denglj.learn.aop.SystemOperation;
import denglj.learn.mybatis.entity.User;
import denglj.learn.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @SystemOperation(code = "001", desc = "查询用户列表")
    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(){
        List<User> result = userMapper.selectList(null);
        return result;
    }

    @SystemOperation(code = "002", desc = "加载用户", template = "来自${from}的调用，请求${id}的信息...")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") String id, @RequestParam(defaultValue = "empty") String from){
        return userMapper.selectById(id);
//        return new User("1", "denglj");
    }

    @SystemOperation(code = "003", desc = "新增用户", template = "新增用户:${user.name}")
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

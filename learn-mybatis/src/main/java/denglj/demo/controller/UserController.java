package denglj.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import denglj.demo.entity.User;
import denglj.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by denglj on 2019/7/2.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("query")
    public List<User> query(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("age", 20).or().eq("id", 2);
        queryWrapper.eq("age", 20).or(
                p1 -> p1.eq("id","2").like("email", "test")
        );

        List<User> users1 = userMapper.selectList(queryWrapper);
//        List<User> users = userMapper.queryAgeBetween(20, 25);

        return users1;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public User get(@PathVariable String id){
        User user = userMapper.selectById(id);
        return user;
    }
    @RequestMapping(method = RequestMethod.POST)
    public String save(@RequestBody User user){
        userMapper.insert(user);
        return user.getId();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(@RequestBody User user){
        userMapper.updateById(user);
        return user.getId();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable String id){
        userMapper.deleteById(id);
        return id;
    }

}

package denglj.learn.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import denglj.learn.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by denglj on 2019/7/2.
 */
public interface UserMapper extends BaseMapper<User>{

    @Select("select * from user where age >= #{start} and age<=#{end}")
    List<User> queryAgeBetween(@Param("start") int start, @Param("end") int end);
}

package denglj.learn.graphql.book;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import graphql.ExecutionInput;
import graphql.GraphQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GraphQLController {
    @Autowired
    private GraphQL graphQL;
    @RequestMapping(value = "/graphql")
    // 这里定义的一个字符串接口所有的参数，定义对象也是可以的
    public Map<String, Object> graphql(@RequestBody String request) {
        JSONObject req = JSON.parseObject(request);
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                // 需要执行的查询语言
                .query(req.getString("query"))
                // 执行操作的名称，默认为null
                .operationName(req.getString("operationName"))
                // 获取query语句中定义的变量的值
                .variables(req.getJSONObject("variables"))
                .build();
        // 执行并返回结果
        return this.graphQL.execute(executionInput).toSpecification();
    }
}

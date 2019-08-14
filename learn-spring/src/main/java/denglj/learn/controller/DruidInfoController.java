package denglj.learn.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by denglj on 2019/8/13.
 */
@RestController
@RequestMapping("/druid-info")
@Slf4j
public class DruidInfoController {

    @RequestMapping(value = "sql", method = RequestMethod.GET)
    public Object sqlInfo(){
        List<Map<String, Object>> dataSourceStatDataList = DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
        for (Map<String, Object> map : dataSourceStatDataList) {
            log.info(JSON.toJSONString(map));
            Object identity = DruidStatManagerFacade.getInstance().getSqlStatById((Integer)map.get("Identity"));
//            List<Map<String, Object>> sqls = DruidStatManagerFacade.getInstance().getSqlStatDataList(map);
            log.info(JSON.toJSONString(identity));
        }
        return null;
    }
}

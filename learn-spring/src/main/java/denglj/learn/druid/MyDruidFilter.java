package denglj.learn.druid;

import com.alibaba.druid.filter.FilterEventAdapter;
import com.alibaba.druid.proxy.jdbc.StatementProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by denglj on 2019/8/13.
 */
@Component
@Slf4j
public class MyDruidFilter extends FilterEventAdapter {
    @Override
    protected void statementExecuteAfter(StatementProxy statement, String sql, boolean result) {
        log.info(sql);
    }
}

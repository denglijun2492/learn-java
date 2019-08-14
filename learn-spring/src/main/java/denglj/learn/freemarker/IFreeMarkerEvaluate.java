package denglj.learn.freemarker;

import java.util.Map;

/**
 * freemarker字符串模板计算
 * Created by denglj on 2019/7/17.
 */
public interface IFreeMarkerEvaluate {
    /**
     * 字符串计算
     * @param template
     * @param params
     * @return
     */
    String evaluate(String template, Map<String, Object> params);
}

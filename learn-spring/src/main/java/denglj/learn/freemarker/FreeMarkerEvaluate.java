package denglj.learn.freemarker;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by denglj on 2019/7/17.
 */
@Component
public class FreeMarkerEvaluate implements IFreeMarkerEvaluate {

    private Configuration configuration;

    @PostConstruct
    public void init(){
        this.configuration = createConfiguration();
    }

    @Override
    public String evaluate(String template, Map<String, Object> params) {
        try{
            StringWriter stringWriter = new StringWriter();
            Template tpl = new Template("", template, configuration);
            tpl.process(params, stringWriter);
            return stringWriter.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    private Configuration createConfiguration(){
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        configuration.setTemplateLoader(templateLoader);
        configuration.setDefaultEncoding("UTF-8");
        return configuration;
    }

    public static void main(String[] args) {
        FreeMarkerEvaluate freeMarkerEvaluate = new FreeMarkerEvaluate();
        freeMarkerEvaluate.init();
        Map m = new HashMap();
        m.put("name", "邓礼俊");
        m.put("address", "连岳里");
        String s = freeMarkerEvaluate.evaluate("我叫${name}, 我住在${address}", m);
        System.out.println(s);

        s = freeMarkerEvaluate.evaluate("这是${name}, 他住在${address}", m);
        System.out.println(s);

    }
}

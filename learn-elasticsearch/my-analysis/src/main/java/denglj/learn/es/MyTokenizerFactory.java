package denglj.learn.es;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenizerFactory;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/19 16:34
 **/
public class MyTokenizerFactory extends AbstractTokenizerFactory {

    public MyTokenizerFactory(IndexSettings indexSettings, String ignored, Settings settings) {
        super(indexSettings, ignored, settings);
    }

    public MyTokenizerFactory(IndexSettings indexSettings, Environment environment, String ignored, Settings settings) {
        super(indexSettings, ignored, settings);
    }

    public Tokenizer create() {
        return new MyTokenizer();
    }
}

package denglj.learn.es;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractIndexAnalyzerProvider;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/19 16:34
 **/
public class MyAnalyzerProvider extends AbstractIndexAnalyzerProvider<MyAnalyzer> {

    private final MyAnalyzer myAnalyzer;

    public MyAnalyzerProvider(IndexSettings indexSettings, String name, Settings settings) {
        super(indexSettings, name, settings);
        myAnalyzer = new MyAnalyzer();
    }

    public MyAnalyzerProvider(IndexSettings indexSettings, Environment environment, String name, Settings settings) {
        super(indexSettings, name, settings);
        myAnalyzer = new MyAnalyzer();
    }

    public MyAnalyzer get() {
        return myAnalyzer;
    }
}

package denglj.learn.es;

import org.apache.lucene.analysis.Analyzer;

/**
 * @Title
 * @Description: TODO
 * @Author denglj
 * @Date 2019/11/19 16:33
 **/
public class MyAnalyzer extends Analyzer {
    protected TokenStreamComponents createComponents(String s) {
        return new TokenStreamComponents(new MyTokenizer());
    }
}

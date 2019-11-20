package denglj.learn.es;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.AnalyzerProvider;
import org.elasticsearch.index.analysis.TokenizerFactory;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.Collections;
import java.util.Map;

public class MyAnalisisPlugin extends Plugin implements AnalysisPlugin {

    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {

        return Collections.singletonMap("my-word", MyTokenizerFactory::new);
    }

    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {

        return Collections.singletonMap("my-word", MyAnalyzerProvider::new);
    }
}

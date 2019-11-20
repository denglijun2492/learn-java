package denglj.learn.elasticsearch.querydsl;

import denglj.learn.elasticsearch.ElasticSearchDataSource;
import denglj.learn.elasticsearch.ElasticSearchPrint;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TermQueryDemo {

    private static Logger Log = LoggerFactory.getLogger(TermQueryDemo.class);

    public static void main(String[] args) throws Exception{
        queryByTerm();
    }

    public static void queryByTerm() throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(
                QueryBuilders.termQuery("gender", "1")
        );
        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = ElasticSearchDataSource.instance().getRestClient().search(searchRequest);

        ElasticSearchPrint.print(searchResponse);
    }
}

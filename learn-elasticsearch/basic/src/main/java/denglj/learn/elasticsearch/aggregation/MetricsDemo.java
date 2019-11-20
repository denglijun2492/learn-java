package denglj.learn.elasticsearch.aggregation;

import denglj.learn.elasticsearch.ElasticSearchDataSource;
import denglj.learn.elasticsearch.ElasticSearchPrint;
import denglj.learn.elasticsearch.querydsl.TermQueryDemo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.script.Script;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.metrics.NumericMetricsAggregation;
import org.elasticsearch.search.aggregations.metrics.cardinality.Cardinality;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.min.Min;
import org.elasticsearch.search.aggregations.metrics.percentiles.ParsedPercentiles;
import org.elasticsearch.search.aggregations.metrics.percentiles.Percentile;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsDemo {

    private static Logger Log = LoggerFactory.getLogger(TermQueryDemo.class);

    public static void main(String[] args)throws Exception{
        try {
//            statByMinMax();
//            statByAvgSumScript();
//            statByCount();
//            statByCardinality();
//            statByStats();
            statByPercentiles();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ElasticSearchDataSource.instance().getRestClient().close();
        }

    }

    /**
     * 统计最小和最大值
     * @throws Exception
     */
    public static void statByMinMax()throws Exception{
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(
                AggregationBuilders.min("min_birthday").field("birthday")
        ).aggregation(
                AggregationBuilders.min("max_birthday").field("birthday")
        );

        sourceBuilder.size(0);

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restClient.search(searchRequest);

        ElasticSearchPrint.print(sourceBuilder);

        Log.info("统计结果如下：");
        Min min_birthday = searchResponse.getAggregations().get("min_birthday");
        Log.info("name:{},value:{}", min_birthday.getName(), min_birthday.getValueAsString());
        Max max_birthday = searchResponse.getAggregations().get("max_birthday");
        Log.info("name:{},value:{}", max_birthday.getName(), max_birthday.getValueAsString());
    }

    /**
     * 统计平均值、汇总值，根据script方式
     * @throws Exception
     */
    public static void statByAvgSumScript()throws Exception{
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.aggregation(
                AggregationBuilders.avg("avg_age").script(new Script("doc.age.value+10")).missing(100)
        ).aggregation(
                AggregationBuilders.sum("sum_age").field("age").script(new Script("_value*1.1"))
        );

        sourceBuilder.size(0);

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);

        SearchResponse searchResponse = restClient.search(searchRequest);

        Log.info("返回结果：");
        for (Aggregation aggregation : searchResponse.getAggregations()) {
            NumericMetricsAggregation.SingleValue v = (NumericMetricsAggregation.SingleValue)aggregation;
            Log.info("name:{},value:{}",v.getName(), v.getValueAsString());
        }
    }

    /**
     * 统计count数量，正常hints返回记录数，aggregation的count是按value不为空统计的
     * @throws Exception
     */
    public static void statByCount()throws Exception{
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(
                QueryBuilders.termQuery("gender", "1")
        );
        sourceBuilder.aggregation(
                AggregationBuilders.count("age_count").field("age")
        );

        sourceBuilder.size(0);

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        SearchResponse searchResponse = restClient.search(searchRequest);
        ElasticSearchPrint.print(searchResponse);
        for (Aggregation aggregation : searchResponse.getAggregations()) {
            NumericMetricsAggregation.SingleValue v = (NumericMetricsAggregation.SingleValue)aggregation;
            Log.info("name:{},value:{}",v.getName(), v.getValueAsString());
        }
    }

    /**
     * 去重统计民族数量
     * @throws Exception
     */
    public static void statByCardinality()throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.cardinality("nation_count").field("nation")
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);

        Cardinality stat = searchResponse.getAggregations().get("nation_count");
        Log.info("name:{},value:{}", stat.getName(), stat.getValueAsString());
    }

    /**
     * stats统计count max min avg sum 5个值
     * @throws Exception
     */
    public static void statByStats()throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.stats("stat_age").field("age")
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);

        Stats stat = searchResponse.getAggregations().get("stat_age");
        Log.info("getCount: {}", stat.getCount());
        Log.info("getMax: {}", stat.getMax());
        Log.info("getMin: {}", stat.getMin());
        Log.info("getAvg: {}", stat.getAvg());
        Log.info("getSum: {}", stat.getSum());
    }

    /**
     * 例7 Percentiles百分比占比统计
     * @throws Exception
     */
    public static void statByPercentiles()throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.percentiles("age_percents").field("age")
        );
        sourceBuilder.aggregation(
                AggregationBuilders.percentiles("age_percents2").field("age").percentiles(25, 50, 75)
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);

        ParsedPercentiles p1 = searchResponse.getAggregations().get("age_percents");
        Log.info("-----------------p1------------------");
        for (Percentile p : p1) {
            Log.info("{}% = {}",p.getPercent(), p.getValue());
        }
        Log.info("-----------------p2------------------");
        ParsedPercentiles p2 = searchResponse.getAggregations().get("age_percents2");
        for (Percentile p : p2) {
            Log.info("{}% = {}",p.getPercent(), p.getValue());
        }
    }
}

package denglj.learn.elasticsearch.aggregation;

import denglj.learn.elasticsearch.ElasticSearchDataSource;
import denglj.learn.elasticsearch.ElasticSearchPrint;
import denglj.learn.elasticsearch.querydsl.TermQueryDemo;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.InternalOrder;
import org.elasticsearch.search.aggregations.bucket.histogram.DateHistogramInterval;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.histogram.ParsedDateHistogram;
import org.elasticsearch.search.aggregations.bucket.range.ParsedRange;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BucketDemo {

    private static Logger Log = LoggerFactory.getLogger(TermQueryDemo.class);

    public static void main(String[] args)throws Exception {
        try {
//            groupByTermsCount();
//            groupByRange();
            groupByDateHistogram();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            ElasticSearchDataSource.instance().getRestClient().close();
        }
    }

    /**
     * terms分组count数量
     */
    public static void groupByTermsCount()throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.terms("nation_terms")
                        .field("nation")
                        .size(10)
                        .order(InternalOrder.aggregation("_count", false))
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);
        Terms terms = searchResponse.getAggregations().get("nation_terms");
        Log.info("统计结果：");
        for (Terms.Bucket bucket : terms.getBuckets()) {
            Log.info("{} -> {}", bucket.getKey(), bucket.getDocCount());
        }
    }

    /**
     * range范围分组聚合
     */
    public static void groupByRange()throws Exception{
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.range("age_range")
                        .field("age")
                        .addUnboundedTo(30)
                        .addRange(30, 50)
                        .addUnboundedFrom(50)
                        .subAggregation(
                                AggregationBuilders.max("birthday_max").field("birthday")
                        )
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);
        ParsedRange range = searchResponse.getAggregations().get("age_range");
        Log.info("统计结果：");
        for (Range.Bucket bucket : range.getBuckets()) {
            Log.info("{} -> {}，birthday_max->{}",
                    bucket.getKey(),
                    bucket.getDocCount(),
                    ((Max)bucket.getAggregations().get("birthday_max")).getValueAsString());
        }
    }

    /**
     * date_histogram按年月日聚合统计直方图
     */
    public static void groupByDateHistogram()throws Exception {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.size(0);
        sourceBuilder.aggregation(
                AggregationBuilders.dateHistogram("birthday_stat")
                        .field("birthday")
                        .dateHistogramInterval(DateHistogramInterval.YEAR)
                        .subAggregation(
                                AggregationBuilders.sum("age_sum").field("age")
                        )
        );

        SearchRequest searchRequest = new SearchRequest("my_person");
        searchRequest.types("_doc");
        searchRequest.source(sourceBuilder);

        ElasticSearchPrint.print(sourceBuilder);
        RestHighLevelClient restClient = ElasticSearchDataSource.instance().getRestClient();
        SearchResponse searchResponse = restClient.search(searchRequest);
        ParsedDateHistogram histogram = searchResponse.getAggregations().get("birthday_stat");
        Log.info("统计结果：");
        for (Histogram.Bucket bucket : histogram.getBuckets()) {
            Log.info("{} -> {}，age_sum->{}",
                    bucket.getKey(),
                    bucket.getDocCount(),
                    ((Sum) bucket.getAggregations().get("age_sum")).getValueAsString());
        }
    }
}

package denglj.learn.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticSearchPrint {
    private static Logger log = LoggerFactory.getLogger(ElasticSearchPrint.class);

    public static void print(SearchSourceBuilder sourceBuilder){
        log.info("请求报文：");
        log.info(JSON.toJSONString(JSON.parseObject(sourceBuilder.toString()), SerializerFeature.PrettyFormat));
    }

    /**
     * 打印结果信息
     * @param response
     */
    public static void print(SearchResponse response){
        log.info("-------------------------------------------");
        log.info("请求结果：");
        log.info("http状态码：{}", response.status());
        log.info("查询耗费时间：{}ms", response.getTook().getMillis());
        log.info("是否超时：", response.isTimedOut());
        log.info("影响分片数：", response.getTotalShards());
        log.info("成功分片数：", response.getSuccessfulShards());
        log.info("失败分片数：", response.getFailedShards());
        for (ShardSearchFailure shardSearchFailure : response.getShardFailures()) {
            log.info(shardSearchFailure.reason());
        }
        log.info("总记录数：{}", response.getHits().getTotalHits());
        log.info("当前返回记录数：{}", response.getHits().getHits().length);
        log.info("命中的最大分值：{}", response.getHits().getMaxScore());
        log.info("数据如下：");
        for (SearchHit hit : response.getHits().getHits()) {
            log.info("score:{}, id:{}, source:{}", hit.getScore(), hit.getId(), hit.getSourceAsString());
        }
    }
}

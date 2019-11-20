package denglj.learn.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.yaml.snakeyaml.Yaml;

import java.util.ArrayList;
import java.util.List;

public class ElasticSearchDataSource {

    private static Logger Log = LoggerFactory.getLogger(ElasticSearchDataSource.class);

    private ElasticSearchParameter parameter;

    private static ElasticSearchDataSource dataSource;

    /**
     * rest连接客户端
     */
    private RestHighLevelClient restClient;
    /**
     * java客户端
     */
    private TransportClient transportClient;
    /**
     * jdbc访问客户端
     */
    private JdbcTemplate jdbcTemplate;

    private ElasticSearchDataSource(){
        Yaml yaml = new Yaml();
        parameter = yaml.loadAs(
                ElasticSearchDataSource.class.getResourceAsStream("/es-datasource.yml"),
                ElasticSearchParameter.class);
    }

    public static ElasticSearchDataSource instance(){
        if(dataSource == null){
            dataSource = new ElasticSearchDataSource();
        }
        return dataSource;
    }

    /**
     * 获取rest的访问客户端
     *
     * @return
     */
    public synchronized RestHighLevelClient getRestClient() {
        if (restClient == null) {
            //1.添加访问节点列表
            List<HttpHost> hosts = new ArrayList<>();
            for (ElasticSearchParameter.HttpHost h : this.parameter.getHosts()) {
                HttpHost host = new HttpHost(h.getHostname(), h.getPort(), h.getScheme());
                hosts.add(host);
            }
            HttpHost[] hostArr = hosts.toArray(new HttpHost[hosts.size()]);
            RestClientBuilder restClientBuilder = RestClient.builder(hostArr);

            //2.配置用户名密码
            if (parameter.getSecurityEnabled().equals("1")) {
                HttpClientSecurityConfigCallback securityConfigCallback = new HttpClientSecurityConfigCallback(parameter);
                restClientBuilder.setHttpClientConfigCallback(securityConfigCallback);
            }

            //3. 实例化client
            restClient = new RestHighLevelClient(restClientBuilder);

        }
        return restClient;
    }

    /**
     * 获取jdbc的访问客户端
     *
     * @return
     */
    public synchronized JdbcTemplate getJdbcTemplate() {
//        if (jdbcTemplate == null) {
//            String tempUrl = "jdbc:es://${SCHEME}://${HOSTNAME}:${PORT}";
//            String jdbcUrl = JspExpressionUtil.evaluate(tempUrl, BeanUtils.objToMap(parameter.getHosts().get(0)));
//            JdbcDataSource ds = new JdbcDataSource();
//            ds.setUrl(jdbcUrl);
//            if (parameter.getSecurityEnabled().equals("1")) {
//                Properties ps = new Properties();
//                ps.put("user", parameter.getUserName());
//                ps.put("password", parameter.getPassword());
//                ds.setProperties(ps);
//            }
//            jdbcTemplate = new JdbcTemplate(ds);
//        }
        return jdbcTemplate;
    }

    /**
     * 获取transport客户端
     *
     * @return
     */
    public synchronized TransportClient getTransportClient() {
//        if (transportClient == null) {
//
//            if (parameter.getSecurityEnabled() != null && parameter.getSecurityEnabled().equals("1")) {
//                Settings settings = Settings.builder()
//                        .put("xpack.security.user", parameter.getUserName() + ":" + parameter.getPassword())
//                        .put("xpack.security.enabled", "true")
//                        .put("xpack.security.transport.ssl.enabled", "true")
//                        .put("cluster.name", parameter.getClusterName())
//                        .put("xpack.security.transport.ssl.verification_mode", "certificate")
//                        .put("xpack.security.transport.ssl.keystore.path", getPath(parameter.getTrustStorePath()))
//                        .put("xpack.security.transport.ssl.truststore.path", getPath(parameter.getTrustStorePath()))
//                        .build();
//                transportClient = new PreBuiltXPackTransportClient(settings);
//            } else {
//                Settings settings = Settings.builder()
//                        .put("cluster.name", parameter.getClusterName())
//                        .build();
//                transportClient = new PreBuiltTransportClient(settings);
//            }
//            try {
//                for (ElasticSearchParameter.HttpHost h : this.parameter.getHosts()) {
//                    transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(h.getHostname()), h.getTransPort()));
//                }
//            } catch (UnknownHostException e) {
//                Log.error(e.getMessage(), e);
//            }
//        }

        return transportClient;
    }

}

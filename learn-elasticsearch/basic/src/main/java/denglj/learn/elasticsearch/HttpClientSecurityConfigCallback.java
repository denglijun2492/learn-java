package denglj.learn.elasticsearch;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClientBuilder;

/**
 * 设置restClient的用户密码
 * Created by denglj on 2019/1/25.
 */
public class HttpClientSecurityConfigCallback implements RestClientBuilder.HttpClientConfigCallback {

    private ElasticSearchParameter parameter;

    private CredentialsProvider credentialsProvider;

    public HttpClientSecurityConfigCallback(ElasticSearchParameter parameter){
        this.parameter = parameter;
        this.credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(parameter.getUserName(), parameter.getPassword()));
    }

    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
        return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
    }
}

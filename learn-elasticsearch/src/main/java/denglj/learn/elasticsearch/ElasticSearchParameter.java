package denglj.learn.elasticsearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接配置参数
 * Created by denglj on 2018/8/22.
 */
public class ElasticSearchParameter {

    /**
     * 节点主机列表
     */
    private List<HttpHost> hosts = new ArrayList<>();
    /**
     * 安全登录开关
     */
    private String securityEnabled = "0";
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 证书地址
     */
    private String trustStorePath;
    /**
     * 集群名称
     */
    private String clusterName;

    public static class HttpHost {
        private String hostname;
        private Integer port;
        private String scheme;
        private Integer transPort;

        public String getHostname() {
            return hostname;
        }

        public void setHostname(String hostname) {
            this.hostname = hostname;
        }

        public Integer getPort() {
            return port;
        }

        public void setPort(Integer port) {
            this.port = port;
        }

        public String getScheme() {
            return scheme;
        }

        public void setScheme(String scheme) {
            this.scheme = scheme;
        }

        public Integer getTransPort() {
            return transPort;
        }

        public void setTransPort(Integer transPort) {
            this.transPort = transPort;
        }
    }

    public List<HttpHost> getHosts() {
        return hosts;
    }

    public void setHosts(List<HttpHost> hosts) {
        this.hosts = hosts;
    }

    public String getSecurityEnabled() {
        return securityEnabled;
    }

    public void setSecurityEnabled(String securityEnabled) {
        this.securityEnabled = securityEnabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrustStorePath() {
        return trustStorePath;
    }

    public void setTrustStorePath(String trustStorePath) {
        this.trustStorePath = trustStorePath;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }
}

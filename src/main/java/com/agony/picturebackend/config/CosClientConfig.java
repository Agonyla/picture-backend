package com.agony.picturebackend.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Agony
 * @create: 2025/2/26 20:12
 * @describe: 读取配置文件
 */


@Configuration
@ConfigurationProperties(prefix = "cos.client")
@Data
public class CosClientConfig {

    /**
     * 域名
     */
    private String host;

    /**
     * secretId
     */
    private String secretId;

    /**
     * 密钥（注意不要泄露）
     */
    private String secretKey;

    /**
     * 区域
     */
    private String region;

    /**
     * 桶名
     */
    private String bucket;


    @Bean
    public COSClient cosClient() {
        // 初始化用户身份信息(secretId, secretKey)
        BasicCOSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 设置bucket的区域, COS地域的简称请参照 https://www.qcloud.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(region));

        return new COSClient(cred, clientConfig);
    }


}

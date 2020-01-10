package com.xzixi.util.sftp.client.multiple.starter.autoconfigure;

import com.xzixi.util.sftp.client.component.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 薛凌康
 */
@Configuration
@ConditionalOnClass(SftpPool.class)
@EnableConfigurationProperties(SftpClientMultipleProperties.class)
public class SftpClientMultipleAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MultipleSftpClient multipleSftpClient(SftpClientMultipleProperties sftpClientMultipleProperties) {
        MultipleSftpClient multipleSftpClient = new MultipleSftpClient();
        sftpClientMultipleProperties.getProperties().forEach((name, properties) -> {
            SftpFactory sftpFactory = new SftpFactory.Builder().properties(properties).build();
            SftpPoolConfig sftpPoolConfig = new SftpPoolConfig.Builder().properties(properties).build();
            SftpAbandonedConfig sftpAbandonedConfig = new SftpAbandonedConfig.Builder().properties(properties).build();
            SftpPool sftpPool = new SftpPool(sftpFactory, sftpPoolConfig, sftpAbandonedConfig);
            ISftpClient sftpClient = new SftpClient(sftpPool);
            multipleSftpClient.put(name, sftpClient);
        });
        return multipleSftpClient;
    }
}

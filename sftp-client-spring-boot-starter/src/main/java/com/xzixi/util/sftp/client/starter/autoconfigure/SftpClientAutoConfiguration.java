package com.xzixi.util.sftp.client.starter.autoconfigure;

import com.xzixi.util.sftp.client.component.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * sftp连接池自动配置
 *
 * @author 薛凌康
 */
@Configuration
@ConditionalOnClass(SftpPool.class)
@EnableConfigurationProperties(SftpClientProperties.class)
public class SftpClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SftpFactory sftpFactory(SftpClientProperties sftpClientProperties) {
        return new SftpFactory.Builder().properties(sftpClientProperties).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SftpPoolConfig sftpPoolConfig(SftpClientProperties sftpClientProperties) {
        return new SftpPoolConfig.Builder().properties(sftpClientProperties).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SftpAbandonedConfig sftpAbandonedConfig(SftpClientProperties sftpClientProperties) {
        return new SftpAbandonedConfig.Builder().properties(sftpClientProperties).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SftpPool sftpPool(SftpFactory sftpFactory,
                             SftpPoolConfig sftpPoolConfig,
                             SftpAbandonedConfig sftpAbandonedConfig) {
        return new SftpPool(sftpFactory, sftpPoolConfig, sftpAbandonedConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public SftpClient sftpClient(SftpPool sftpPool) {
        return new SftpClient(sftpPool);
    }
}

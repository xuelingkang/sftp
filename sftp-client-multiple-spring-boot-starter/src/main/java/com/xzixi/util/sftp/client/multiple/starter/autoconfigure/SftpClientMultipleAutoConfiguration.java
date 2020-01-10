package com.xzixi.util.sftp.client.multiple.starter.autoconfigure;

import com.xzixi.util.sftp.client.component.SftpPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author 薛凌康
 */
@Configuration
@ConditionalOnClass(SftpPool.class)
@EnableConfigurationProperties(SftpClientMultipleProperties.class)
public class SftpClientMultipleAutoConfiguration {
}

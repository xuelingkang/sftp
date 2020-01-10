package com.xzixi.util.sftp.client.starter.autoconfigure;

import com.xzixi.util.sftp.client.properties.Properties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * sftp连接池配置参数
 *
 * @author 薛凌康
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "sftp-client")
public class SftpClientProperties extends Properties {

}

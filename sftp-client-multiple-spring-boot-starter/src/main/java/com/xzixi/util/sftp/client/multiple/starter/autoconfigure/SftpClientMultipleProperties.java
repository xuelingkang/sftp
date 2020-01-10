package com.xzixi.util.sftp.client.multiple.starter.autoconfigure;

import com.xzixi.util.sftp.client.properties.CommonProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author 薛凌康
 */
@Data
@ConfigurationProperties(prefix = "sftp-client-multiple")
public class SftpClientMultipleProperties {

    private Map<String, CommonProperties> properties = new LinkedHashMap<>();
}

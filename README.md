# sftp连接池和springboot启动器

## 介绍

<a href="https://blog.csdn.net/qq_35433926" target="_blank">博客主页</a>

## 使用方法

项目已经发布到maven中央仓库，直接在pom.xml中引用即可

```xml
<dependencies>
    <dependency>
        <groupId>com.xzixi</groupId>
        <artifactId>sftp-client-spring-boot-starter</artifactId>
        <version>2.1.1</version>
    </dependency>
</dependencies>
```

### 单个sftp源

#### yml配置

```yaml
# 只列举主要配置
sftp-client:
  # 主机ip
  host: host
  # 端口号
  port: 22
  # 用户名
  username: root
  # 密码
  password: root
```

#### java代码

```java
import com.xzixi.util.sftp.client.component.SftpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SftpDemoApplicationTests {

    @Autowired
    private ISftpClient sftpClient;

    @Test
    public void testSftp() {
        sftpClient.open(sftp -> {
            // 执行sftp操作
            sftp.delete("/root", "init.sql");
        });
    }
}
```

### 多个sftp源

#### yml配置

```yaml
# 只列举主要配置，其他配置同单个sftp源
sftp-client:
  multiple: true
  clients:
    client1:
      # 主机ip
      host: host1
      # 端口号
      port: 22
      # 用户名
      username: root
      # 密码
      password: root
    client2:
      # 主机ip
      host: host2
      # 端口号
      port: 22
      # 用户名
      username: root
      # 密码
      password: root
```

#### java代码

```java
import com.xzixi.util.sftp.client.multiple.starter.autoconfigure.MultipleSftpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SftpDemoApplicationTests {

    @Autowired
    private ISftpClient sftpClient;

    @Test
    public void testSftp() {
        // 选择client1
        ((MultipleSftpClient) sftpClient).choose("client1");
        sftpClient.open(sftp -> {
            // 执行sftp操作
            sftp.delete("/root", "init.sql");
        });
    }
}
```

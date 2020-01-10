# sftp连接池和springboot启动器

## 介绍

重构中

启动器基于springboot2.1.3.RELEASE

<a href="https://blog.csdn.net/qq_35433926" target="_blank">博客主页</a>

## 使用方法

### 直接使用

项目已经发布到maven中央仓库，直接在pom.xml中引用即可

```xml
<dependencies>
    <dependency>
        <groupId>com.xzixi</groupId>
        <artifactId>sftp-pool-spring-boot-starter</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```

### 修改后使用

1. 下载项目<br>
打开git bash窗口，执行命令`git clone git@gitee.com:xuelingkang/sftp.git`

2. 编译并安装到本地maven仓库<br>
进入工程目录，打开cmd窗口，执行命令`mvn clean install -Dmaven.test.skip=true`

3. 在自己的项目中引用

```xml
<dependencies>
    <dependency>
        <groupId>com.xzixi</groupId>
        <artifactId>sftp-pool-spring-boot-starter</artifactId>
        <version>1.0</version>
    </dependency>
</dependencies>
```

## yml配置

```yaml
sftp-pool:
  # 主机ip
  host: 192.168.234.201
  # 端口号
  port: 22
  # 用户名
  username: root
  # 密码
  password: root
  # 连接池配置
  pool:
    # 对象最大数量
    max-total: 20
    # 最大空闲对象数量
    max-idle: 10
    # 最小空闲对象数量
    min-idle: 5
    # 表示使用FIFO获取对象
    lifo: true
    # 不使用lock的公平锁
    fairness: false
    # 获取一个对象的最大等待时间
    max-wait-millis: 5000
    # 对象最小的空闲时间
    min-evictable-idle-time-millis: -1
    # 驱逐线程的超时时间
    evictor-shutdown-timeout-millis: 10000
    # 对象最小的空间时间，保留最小空闲数量
    soft-min-evictable-idle-time-millis: 1800000
    # 检测空闲对象线程每次检测的空闲对象的数量
    num-tests-per-eviction-run: 3
    # 在创建对象时检测对象是否有效
    test-on-create: false
    # 在从对象池获取对象时是否检测对象有效
    test-on-borrow: false
    # 在向对象池中归还对象时是否检测对象有效
    test-on-return: false
    # 在检测空闲对象线程检测到对象不需要移除时，是否检测对象的有效性
    test-while-idle: true
    # 空闲对象检测线程的执行周期
    time-between-eviction-runs-millis: 600000
    # 当对象池没有空闲对象时，新的获取对象的请求是否阻塞
    block-when-exhausted: true
    # 是否注册JMX
    jmx-enabled: false
    # JMX前缀，当jmxEnabled为true时有效
    jmx-name-prefix: pool
    # 使用jmxNameBase + jmxNamePrefix + i来生成ObjectName，当jmxEnabled为true时有效
    jmx-name-base: sftp
  # 废弃对象跟踪配置
  abandoned:
    # 从对象池中获取对象的时候进行清理
    remove-abandoned-on-borrow: true
    # 池维护（evicor）是否执行放弃的对象删除
    remove-abandoned-on-maintenance: true
    # 对象被获取后多长时间没有返回给对象池，则放弃对象
    remove-abandoned-timeout: 300
    # 是否记录放弃对象的应用程序代码的堆栈跟踪
    log-abandoned: false
    # 是否记录完整堆栈信息，当logAbandoned为true时有效
    require-full-stack-trace: false
    # 如果池实现了org.apache.commons.pool2.UsageTracking接口，是否记录完整堆栈信息用来辅助调试废弃对象，当logAbandoned为true时有效
    use-usage-tracking: false
```

详细用法请参考源码注释和案例工程`sfpt-pool-demo`

## 欢迎issue和star！

package org.wmg.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

// <!--通过configLocation使用其他配置文件配置，但是configLocation与configuration不能共存-->
//@Configuration
public class DataSourceConfig {
//    @Value("${test.datasource.url}")
//    private String url;
//
//    @Value("${test.datasource.username}")
//    private String user;
//
//    @Value("${test.datasource.password}")
//    private String password;
//
//    @Value("${test.datasource.driverClassName}")
//    private String driverClass;
//
//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        PooledDataSource dataSource = new PooledDataSource();
//        dataSource.setDriver(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(DruidDataSource.class).build();
    }
}
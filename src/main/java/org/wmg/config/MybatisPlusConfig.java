package org.wmg.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MybatisPlusConfig {
//    @Autowired
    @Autowired
    DataSource dataSource;
    /**
     * myBatisPlus 分页插件
     */
    @Bean(name="mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return mybatisPlusInterceptor;
    }



    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    // 调整 SqlSessionFactory 为 Mybatis-Plus 的 MybatisSqlSessionFactoryBean
    @Bean("sqlSessionFactory")
    MybatisSqlSessionFactoryBean bean() throws IOException {
        //mybatis
        //final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //mybatis-plus
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 设置映射文件路径
//        bean.setMapperLocations(new ClassPathResource("mapper/*.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml"));
        // 设置分页插件  //这行没有 total 、pages 会为空
        bean.setPlugins(mybatisPlusInterceptor());
        System.out.println("加载插件完成");
        //mybatis
        // final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        //mybatis-plus
        return bean;
    }


//    @Bean
//    public MybatisPlusInterceptor mybatisPlusInterceptor() {
//        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
//        //添加：分页插件
//        //参数：new PaginationInnerInterceptor(DbType.MYSQL)，是专门为mysql定制实现的内部的分页插件
//        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
//        //添加：乐观锁插件
//        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
//        return interceptor;
//    }
}

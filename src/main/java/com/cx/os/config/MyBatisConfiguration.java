package com.cx.os.config;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.IOException;


@Configuration
@MapperScan({"com.cx.os.dao"})
@PropertySource("classpath:project.properties")
public class MyBatisConfiguration {

    //使用注解来读取properties文件
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.host}")
    private String host;
    @Value("${jdbc.database}")
    private String database;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;


    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver);
        String url = "jdbc:mysql://"+host+":3306/"+database+"?useUnicode=true&amp;characterEncoding=utf-8&amp;zeroDateTimeBehavior=convertToNull";
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }
	@Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

	@Bean
    public SqlSessionTemplate sqlSessionTemplateBean(){

		//VFS.addImplClass(SpringBootVFS.class);

		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
      //  Resource[] resources = resolver.getResources("classpath:mapper/*Mapper.xml");
//        Resource[] resources1 = resolver.getResources("classpath*:mapper/*Mapper.xml");
// System.out.println("resources.length=-===>"+resources.length);
//        System.out.println("resources1.length=-===>"+resources1.length);
//
//        for(Resource r:resources1) {
//        	System.out.println("Mapper.file name===>"+r.getFilename());
//        }
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/*Mapper.xml"));
            sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:mybatis-config.xml"));
            return new SqlSessionTemplate(sqlSessionFactoryBean.getObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Bean
    public PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }


}

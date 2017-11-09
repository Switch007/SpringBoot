package com.switch007.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.github.pagehelper.PageHelper;

@Configuration
@EnableTransactionManagement
public class MybatisConfig {

	@Autowired
	DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setTypeAliasesPackage("com.switch007.model");

		// 支持下划线到驼峰
		org.apache.ibatis.session.Configuration conf = new org.apache.ibatis.session.Configuration();
		conf.setMapUnderscoreToCamelCase(true);
		bean.setConfiguration(conf);

		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "check");
		properties.setProperty("params", "pageNum=page;pageSize=rows;orderBy=orderBy");
		pageHelper.setProperties(properties);

		// 添加插件
		bean.setPlugins(new Interceptor[] { pageHelper });

		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			// 读取多个文件下的xml文件
			List<Resource> resources = new ArrayList<Resource>(8);
			Resource[] resources1 = resolver.getResources("classpath*:mapper/*.xml");
			//Resource[] resources2 = resolver.getResources("classpath*:sqlmapperext/*.xml");
			resources.addAll(Arrays.asList(resources1));
			//resources.addAll(Arrays.asList(resources2));
			bean.setMapperLocations(resources.toArray(new Resource[] {}));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}

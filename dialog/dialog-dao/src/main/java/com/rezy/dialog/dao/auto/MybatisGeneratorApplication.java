package com.rezy.dialog.dao.auto;

import java.util.Arrays;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.PluginConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @ClassName: MybatisGeneratorApplication
 * @Description: 生成pojo、mapper主方法
 * @author: jun.li
 * @date: 2019年3月23日 下午3:52:18 
 */
public class MybatisGeneratorApplication {

	public static void main(String[] args) {
		// 表
		List<String> tableNames = Arrays.asList("t_user");
		// 配置
		MybatisAutoConfig config = new MybatisAutoConfig();
		// 新功能开发，在主包路径下新建独立的包路径
		config.setFeaturePackageSuffix(null);
		// 代码生成
		generate(config, tableNames);
	}

	private static void generate(MybatisAutoConfig config, List<String> tableNames) {
		Context context = new Context(null);
		try {
			// jdbc
			jdbc(config, context);
			// dao
			dao(config, context);
			// model
			model(config, context);
			// xml mapper
			xmlMapper(config, context);
			// resolver
			resolver(context);
			// plugin
			plugin(config, context);
			// tables
			tables(config, tableNames, context);
			// generate
			Configuration configuration = new Configuration();
			context.setId("mybatis");
			configuration.addContext(context);
			DefaultShellCallback call = new DefaultShellCallback(config.isOverwrite());
			MyBatisGenerator gen = new MyBatisGenerator(configuration, call, null);
			gen.generate(null);
			System.out.println("代码成功生成!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void jdbc(MybatisAutoConfig config, Context context) {
		JDBCConnectionConfiguration jdbc = new JDBCConnectionConfiguration();
		jdbc.setUserId(config.getUserId());
		jdbc.setPassword(config.getPassword());
		jdbc.setConnectionURL(config.getConnectionURL());
		jdbc.setDriverClass(config.getDriverClass());
		/*mysql驱动是6.x及以上配置useInformationSchema解决不识别主键问题*/
		jdbc.addProperty("useInformationSchema", "true");
		context.setJdbcConnectionConfiguration(jdbc);
	}

	private static void dao(MybatisAutoConfig config, Context context) {
		JavaClientGeneratorConfiguration dao = new JavaClientGeneratorConfiguration();
		dao.setTargetPackage(config.getDaoTargetPackage());
		dao.setTargetProject(config.getDaoTargetProject());
		dao.setConfigurationType("XMLMAPPER");
		dao.addProperty("enableSubPackages", String.valueOf(config.isEnableSubPackages()));
		context.setJavaClientGeneratorConfiguration(dao);
	}

	private static void model(MybatisAutoConfig config, Context context) {
		boolean trimStrings = false;
		JavaModelGeneratorConfiguration model = new JavaModelGeneratorConfiguration();
		model.setTargetPackage(config.getModelTargetPackage());
		model.setTargetProject(config.getModelTargetProject());
		model.addProperty("trimStrings", String.valueOf(trimStrings));
		model.addProperty("enableSubPackages", String.valueOf(config.isEnableSubPackages()));
		context.setJavaModelGeneratorConfiguration(model);
	}

	private static void xmlMapper(MybatisAutoConfig config, Context context) {
		SqlMapGeneratorConfiguration mapper = new SqlMapGeneratorConfiguration();
		mapper.setTargetPackage(config.getXmlMapperTargetPackage());
		mapper.setTargetProject(config.getXmlMapperTargetProject());
		mapper.addProperty("enableSubPackages", String.valueOf(config.isEnableSubPackages()));
		context.setSqlMapGeneratorConfiguration(mapper);
	}

	private static void tables(MybatisAutoConfig config, List<String> tableNames, Context context) {
		context.getTableConfigurations().clear();
		TableConfigurationCreate tableCreate = new TableConfigurationCreate(context);
		tableCreate.setEnableExample(config.isEnableExample());
		tableCreate.setTableNames(tableNames);
		tableCreate.setTablePrefix(config.getTablePrefix());
		tableCreate.setMapperSuffix(config.getMapperSuffix());
		tableCreate.setObjectSuffix(config.getModelSuffix());
		tableCreate.createAll();	
	}

	private static void plugin(MybatisAutoConfig config, Context context) {
		PluginConfiguration puginConfig = new PluginConfiguration();
		context.addPluginConfiguration(puginConfig);
		puginConfig.addProperty("mergeable", String.valueOf(!config.isOverwrite()));
		puginConfig.setConfigurationType(MybatisGeneratorPlugin.class.getName());
	}

	private static void resolver(Context context) {
		boolean forceBigDecimals = false;
		JavaTypeResolverConfiguration resolver = new JavaTypeResolverConfiguration();
		resolver.addProperty("forceBigDecimals", String.valueOf(forceBigDecimals));
		context.setJavaTypeResolverConfiguration(resolver);
	}

}

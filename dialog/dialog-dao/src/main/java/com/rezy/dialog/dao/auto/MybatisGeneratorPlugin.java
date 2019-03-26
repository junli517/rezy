package com.rezy.dialog.dao.auto;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.IntrospectedTable.TargetRuntime;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * @ClassName: MybatisGeneratorPlugin
 * @Description: 自动生成插件
 * @author: jun.li
 * @date: 2019年3月23日 下午3:51:15 
 */
public class MybatisGeneratorPlugin extends PluginAdapter {

	// 添加序列化
	private boolean suppressJavaInterface;
	// 原有xml是否进行merge, true merge, false 覆盖不merge
	private boolean mergeable;

	@Override
	public void setContext(Context context) {
		super.setContext(context);
		// 设置默认的注释生成器
		CommentGeneratorConfiguration commentCfg = new CommentGeneratorConfiguration();
		commentCfg.setConfigurationType(MybatisCommentGenerator.class.getCanonicalName());
		context.setCommentGeneratorConfiguration(commentCfg);
		// xml格式化
		context.addProperty(PropertyRegistry.CONTEXT_XML_FORMATTER, MybatisFormatter.class.getName());
		// java格式化
		context.addProperty(PropertyRegistry.CONTEXT_JAVA_FORMATTER, MybatisFormatter.class.getName());
		// 支持oracle获取注释
		context.getJdbcConnectionConfiguration().addProperty("remarksReporting", "true");
	}

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		mergeable = Boolean.valueOf(properties.getProperty("mergeable", "false"));
		suppressJavaInterface = Boolean.valueOf(properties.getProperty("suppressJavaInterface"));
	}

	/**
	 * 生成Mapper接口
	 */
	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// @Mapper
		if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3) {
			interfaze.addImportedType(new FullyQualifiedJavaType("org.apache.ibatis.annotations.Mapper"));
			interfaze.addAnnotation("@Mapper");
		}
		return true;
	}

	/**
	 * 拼接SQL语句，生成Mapper文件
	 */
	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		// 覆盖原有xml
		sqlMap.setMergeable(mergeable);
		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		makeSerializable(topLevelClass, introspectedTable);
		return true;
	}

	/**
	 * 添加序列化接口
	 */
	protected void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
		if (!suppressJavaInterface) {
			topLevelClass.addImportedType(serializable);
			topLevelClass.addSuperInterface(serializable);
			Field field = new Field();
			field.setFinal(true);
			field.setInitializationString("1L");
			field.setName("serialVersionUID");
			field.setStatic(true);
			field.setType(new FullyQualifiedJavaType("long"));
			field.setVisibility(JavaVisibility.PRIVATE);

			if (introspectedTable.getTargetRuntime() == TargetRuntime.MYBATIS3_DSQL) {
				context.getCommentGenerator().addFieldAnnotation(field, introspectedTable,
						topLevelClass.getImportedTypes());
			} else {
				context.getCommentGenerator().addFieldComment(field, introspectedTable);
			}
			topLevelClass.getFields().add(0, field);
		}
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

}

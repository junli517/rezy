package com.rezy.dialog.dao.auto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

/**
 * @ClassName: MybatisCommentGenerator
 * @Description: 自动代码注释生成器
 * @author: jun.li
 * @date: 2019年3月23日 下午3:48:12
 */
public class MybatisCommentGenerator implements CommentGenerator {

	/**
	 * class注释
	 */
	@Override
	public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		topLevelClass.addJavaDocLine("\r");
		topLevelClass.addJavaDocLine("/**");
		topLevelClass.addJavaDocLine(" * @Description :");
		topLevelClass.addJavaDocLine(" * @version 1.0");
		topLevelClass.addJavaDocLine(" * @Author jun.li");
		topLevelClass.addJavaDocLine(" * @Date " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		topLevelClass.addJavaDocLine(" */");
	}

	/**
	 * field注释
	 */
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			field.addJavaDocLine("//" + introspectedColumn.getRemarks());
		}
	}

	/**
	 * get method 注释
	 */
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			method.addJavaDocLine("/**");
			StringBuilder sb = new StringBuilder();
			sb.append(" * 获取");
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" */");
		}
	}

	/**
	 * set method 注释
	 */
	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
			method.addJavaDocLine("/**");
			StringBuilder sb = new StringBuilder();
			sb.append(" * 设置");
			sb.append(introspectedColumn.getRemarks());
			method.addJavaDocLine(sb.toString());
			method.addJavaDocLine(" */");
		}
	}

	@Override
	public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addConfigurationProperties(Properties properties) {
	}

	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
	}

	@Override
	public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
	}

	@Override
	public void addJavaFileComment(CompilationUnit compilationUnit) {
	}

	@Override
	public void addComment(XmlElement xmlElement) {
	}

	@Override
	public void addRootComment(XmlElement rootElement) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			Set<FullyQualifiedJavaType> imports) {
	}

	@Override
	public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
	}

}

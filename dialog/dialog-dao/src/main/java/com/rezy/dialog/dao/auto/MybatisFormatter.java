package com.rezy.dialog.dao.auto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.JavaFormatter;
import org.mybatis.generator.api.XmlFormatter;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;

/**
 * @ClassName: MybatisFormatter
 * @Description: Mybatis 自动生成xml、java格式化
 * @author: jun.li
 * @date: 2019年3月23日 下午3:53:16 
 */
public class MybatisFormatter implements XmlFormatter, JavaFormatter {

	protected Context context;

	/**
	 * Xml 格式化
	 */
	@Override
	public String getFormattedContent(Document document) {
		XmlElement xmlEl = document.getRootElement();
		List<Element> els = xmlEl.getElements();
		for (int i = 0; i * 2 + 1 <= els.size(); i++) {
			// xml节点之间换行
			xmlEl.addElement(i * 2 + 1, new TextElement(""));
		}
		StringBuilder xml = new StringBuilder(document.getFormattedContent());
		// 替换 jdbcType
		replace(xml, ",?jdbcType=\"?\\w+\"?", false, "");
		replace(xml, "Base_Column_List", false, "baseColumns");
		return xml.toString();
	}

	/**
	 * Java 格式化
	 */
	@Override
	public String getFormattedContent(CompilationUnit compilationUnit) {
		String code = compilationUnit.getFormattedContent();
		// 第一行代码换行
		code = code.replaceFirst("\\{", "{\r\n");
		if (code.indexOf("public class") != -1) {
			try {
				StringBuilder sb = new StringBuilder(code);
				// 删除属性之间的隔行
				replace(sb, "([^\\)])\\s?;(\r\n){2,}", false, "$1;\r\n");
				// 方法之间增加换行
				replace(sb, "(;|\\})(\r\n){0,1}(.*\\{\\s+)", false, "$1\r\n\r\n$3");
				// 序列化属性换行
				code = sb.toString().replaceFirst("1L;", "1L;\r\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return code;
	}

	private static void replace(StringBuilder sb, String regex, boolean ignoreCase, String str) {
		Pattern pattern = null;
		if (ignoreCase) {
			pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(regex);
		}
		Matcher matcher = pattern.matcher(sb.toString());
		if (matcher.find()) {
			sb.setLength(0);
			sb.append(matcher.replaceAll(str));
		}
	}

	@Override
	public void setContext(Context context) {
		this.context = context;
	}
	
}

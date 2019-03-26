package com.rezy.dialog.dao.auto;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;

/**
 * @ClassName: TableConfigurationCreate
 * @Description: 动态创建
 * @author: jun.li
 * @date: 2019年3月23日 下午3:50:37 
 */
public class TableConfigurationCreate {

	private Pattern linePattern = Pattern.compile("_(\\w)");

	private Context context;
	private List<String> tableNames;
	private String tablePrefix;
	private boolean enableExample;
	private String modelSuffix;
	private String mapperSuffix;

	public TableConfigurationCreate(Context context) {
		this.context = context;
		this.enableExample = false;
	}

	public void createAll() {
		if (tableNames != null) {
			for (String tableName : tableNames) {
				if (tableName == null || "".equals(tableName)) {
					continue;
				}
				tableName = tableName.trim();
				String tableNameFormat = tableName;
				if (tablePrefix != null && tableNameFormat.startsWith(tablePrefix)) {
					tableNameFormat = tableNameFormat.substring(tablePrefix.length());
				}
				tableNameFormat = lineToHump(tableNameFormat);
				String objectName = tableNameFormat;
				String mapperName = tableNameFormat;
				if (modelSuffix != null) {
					objectName += modelSuffix;
				}
				if (mapperSuffix != null) {
					mapperName += mapperSuffix;
				}
				create(tableName, objectName, mapperName, enableExample);
			}
		}
	}

	public void create(String tableName, String objectName, String mapperName, boolean enableExample) {
		TableConfiguration tc = new TableConfiguration(context);
		tc.setTableName(tableName);
		tc.setMapperName(mapperName);
		tc.setDomainObjectName(objectName);
		tc.setUpdateByExampleStatementEnabled(enableExample);
		tc.setSelectByExampleQueryId(String.valueOf(enableExample));
		tc.setSelectByExampleStatementEnabled(enableExample);
		tc.setDeleteByExampleStatementEnabled(enableExample);
		tc.setCountByExampleStatementEnabled(enableExample);
		tc.setUpdateByPrimaryKeyStatementEnabled(true);
		tc.setDeleteByPrimaryKeyStatementEnabled(true);
		tc.setSelectByPrimaryKeyStatementEnabled(true);
		context.addTableConfiguration(tc);
	}

	public String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		// 第一个字母大写
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	public void setTableNames(List<String> tableNames) {
		this.tableNames = tableNames;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public void setObjectSuffix(String modelSuffix) {
		this.modelSuffix = modelSuffix;
	}

	public void setMapperSuffix(String mapperSuffix) {
		this.mapperSuffix = mapperSuffix;
	}

	public void setEnableExample(boolean enableExample) {
		this.enableExample = enableExample;
	}
}

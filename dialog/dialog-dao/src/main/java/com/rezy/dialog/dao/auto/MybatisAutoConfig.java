package com.rezy.dialog.dao.auto;

/**
 * @ClassName: MybatisAutoConfig
 * @Description: 自动生成配置
 * @author: jun.li
 * @date: 2019年3月23日 下午3:36:25 
 */
public class MybatisAutoConfig {

	// 表前缀
	private String tablePrefix = "t_";
	// 生成实体类后缀
	private String modelSuffix = "Entity";
	// 生成mapper后缀
	private String mapperSuffix = "Dao";
	// 生成model包路径
	private String modelTargetPackage = "com.rezy.dialog.model.entity";
	// 生成model的project路径
	private String modelTargetProject = "../dialog-model/src/main/java";
	// 生成dao包路径
	private String daoTargetPackage = "com.rezy.dialog.dao.mapper";
	// 生成dao的project路径
	private String daoTargetProject = "../dialog-dao/src/main/java";
	// 生成mapper.xml文件的fold路径
	private String xmlMapperTargetPackage = "com.rezy.dialog.dao.mapper";
	// xml mapper project
	private String xmlMapperTargetProject = "../dialog-dao/src/main/resources";
	// jdbc userId
	private String userId = "root";
	// jdbc password
	private String password = "love356@";
	// jdbc driver(MySQL 8.x使用cj.Driver)
	private String driverClass = "com.mysql.cj.jdbc.Driver";
	// jdbc url(MySQL 8.x 需要指定时区)
	private String connectionURL = "jdbc:mysql://localhost:3306/dialog?serverTimezone=UTC";
	// 是否覆盖生成
	private boolean overwrite = true;
	// 是否生成 example
	private boolean enableExample = false;
	// 使用packages
	private boolean enableSubPackages = true;
	// 新功能开发，在主包路径下新建独立的包路径
	private String featurePackageSuffix;

	public String getTablePrefix() {
		return tablePrefix;
	}

	public void setTablePrefix(String tablePrefix) {
		this.tablePrefix = tablePrefix;
	}

	public String getModelSuffix() {
		return modelSuffix;
	}

	public void setModelSuffix(String modelSuffix) {
		this.modelSuffix = modelSuffix;
	}

	public String getMapperSuffix() {
		return mapperSuffix;
	}

	public void setMapperSuffix(String mapperSuffix) {
		this.mapperSuffix = mapperSuffix;
	}

	public boolean isOverwrite() {
		return overwrite;
	}

	public void setOverwrite(boolean overwrite) {
		this.overwrite = overwrite;
	}

	public boolean isEnableExample() {
		return enableExample;
	}

	public void setEnableExample(boolean enableExample) {
		this.enableExample = enableExample;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getXmlMapperTargetPackage() {
		return packageName(xmlMapperTargetPackage);
	}

	public void setXmlMapperTargetPackage(String xmlMapperTargetPackage) {
		this.xmlMapperTargetPackage = xmlMapperTargetPackage;
	}

	public String getXmlMapperTargetProject() {
		return xmlMapperTargetProject;
	}

	public void setXmlMapperTargetProject(String xmlMapperTargetProject) {
		this.xmlMapperTargetProject = xmlMapperTargetProject;
	}

	public boolean isEnableSubPackages() {
		return enableSubPackages;
	}

	public void setEnableSubPackages(boolean enableSubPackages) {
		this.enableSubPackages = enableSubPackages;
	}

	public String getModelTargetPackage() {
		return packageName(modelTargetPackage);
	}

	public void setModelTargetPackage(String modelTargetPackage) {
		this.modelTargetPackage = modelTargetPackage;
	}

	public String getModelTargetProject() {
		return modelTargetProject;
	}

	public void setModelTargetProject(String modelTargetProject) {
		this.modelTargetProject = modelTargetProject;
	}

	public String getDaoTargetPackage() {
		return packageName(daoTargetPackage);
	}

	public void setDaoTargetPackage(String daoTargetPackage) {
		this.daoTargetPackage = daoTargetPackage;
	}

	public String getDaoTargetProject() {
		return daoTargetProject;
	}

	public void setDaoTargetProject(String daoTargetProject) {
		this.daoTargetProject = daoTargetProject;
	}

	/**
	 * 新功能开发，在主包路径下新建独立的包路径
	 */
	public void setFeaturePackageSuffix(String featurePackageSuffix) {
		if (featurePackageSuffix != null && !featurePackageSuffix.startsWith(".")) {
			featurePackageSuffix = "." + featurePackageSuffix;
		}
		this.featurePackageSuffix = featurePackageSuffix;
	}

	private String packageName(String packageName) {
		if (featurePackageSuffix != null) {
			return packageName + featurePackageSuffix;
		}
		return packageName;
	}

}

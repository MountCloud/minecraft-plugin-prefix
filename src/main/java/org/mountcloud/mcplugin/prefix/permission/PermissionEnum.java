package org.mountcloud.mcplugin.prefix.permission;
/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午1:17:07 
 * TODO:
 */
public enum PermissionEnum {
	
	PREFIX_USE("prefix.use"),
	PREFIX_ADMIN("prefix.admin");

	private String value;
	
	private PermissionEnum(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return this.value;
	}

}

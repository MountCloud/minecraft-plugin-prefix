package org.mountcloud.mcplugin.prefix.language;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 上午12:03:03 
 * TODO:
 */
public enum LanguageEnum {
	
	PLUGIN_TITLE("&9prefix"),
	
	COMMAND_USEAGE_HELP("&7/prefix &bhelp &2| Show help"),
	COMMAND_USEAGE_GIVE("&7/prefix &bgive Player prefix &2| give player prefix"),
	COMMAND_USEAGE_REFRESH("&7/prefix &brefresh &2| refresh you prefix"),
	COMMAND_USEAGE_LIST("&7/prefix &blist &2| list you prefixs"),
	COMMAND_USEAGE_USE("&7/prefix &buse index &2| use prefix,index is list command prefix"),
	COMMAND_USEAGE_UNUSE("&7/prefix &bunuse &2|  Cancel use prefix"),
	
	MESSAGE_TITLE("&9[&dprefix&9]"),
	MESSAGE_OPERATION_ERROR("&4Operation error"),
	MESSAGE_REFRESH_PREFIX("&2You refresh prefix succeed"),
	MESSAGE_LIST_PREFIX("&aYou have {0} prefix:"),
	MESSAGE_LIST_PREFIX_DATA("&7{0}: {1}"),
	MESSAGE_GIVE_PREFIX("&aYou Give {0} prefix {1} &asucceed"),
	MESSAGE_GIVE_PREFIX_EXIST("&4{0} prefix {1} &4is exist"),
	MESSAGE_GIVE_PREFIX_NOT_EXIST("&4Index {0} prefix is not exist"),
	MESSAGE_USE_PREFIX("&aYou use prefix {0} &asucceed"),
	MESSAGE_UNUSE_PREFIX("&aYou unuse prefix {0} &asucceed"),
	
	MESSAGE_PLAYER_NOTFOUND("&4Player {0} does not exist or is not online");
	
	private String value;
	
	private LanguageEnum(String val) {
		this.value = val;
	}
	
	public String getValue() {
		return this.value;
	}
	
	/**
	 * 根据enumname 返回enum
	 * @param enumName enum名称
	 * @return 查询的enum
	 */
	public static LanguageEnum getEnumByName(String enumName) {
		LanguageEnum result = null;
		LanguageEnum[] values = LanguageEnum.values();
		for(int i=0;i<values.length;i++) {
			LanguageEnum tempEnum = values[i];
			if(tempEnum.name().equals(enumName)) {
				result = tempEnum;
				break;
			}
		}
		return result;
	}

}

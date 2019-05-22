package org.mountcloud.mcplugin.prefix.util;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午2:01:10 
 * TODO:
 */
public class MessageUtil {
	
	/**
	 * 提供消息
	 * @param messageEnum 消息类型
	 * @return 消息
	 */
	public static String getMessage(LanguageEnum messageEnum,String...prms) {
		String title = PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.MESSAGE_TITLE.name(), LanguageEnum.MESSAGE_TITLE.getValue());
		String message = PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(messageEnum.name(), messageEnum.getValue(),prms);
		return title+" "+message;
	}
	
	/**
	 * 提供消息
	 * @param messageEnum 消息类型
	 * @return 消息
	 */
	public static String getMessage(BaseLanguageEnum messageEnum,String...prms) {
		String title = PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.MESSAGE_TITLE.name(), LanguageEnum.MESSAGE_TITLE.getValue());
		String message = PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(messageEnum.name(), messageEnum.getValue(),prms);
		return title+" "+message;
	}
	
	/**
	 * 发送message
	 * @param sender 接收message的人
	 * @param messageEnum 消息枚举
	 * @param prms 消息参数
	 */
	public static void sendMessage(CommandSender sender,LanguageEnum messageEnum,String...prms) {
		String message = getMessage(messageEnum,prms);
		PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(sender, message);
	}
	
	/**
	 * 发送message
	 * @param sender 接收message的人
	 * @param messageEnum 消息枚举
	 * @param prms 消息参数
	 */
	public static void sendMessage(CommandSender sender,BaseLanguageEnum messageEnum,String...prms) {
		String message = getMessage(messageEnum,prms);
		PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(sender, message);
	}

}

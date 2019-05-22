package org.mountcloud.mcplugin.prefix.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;
import org.mountcloud.mcplugin.prefix.util.MessageUtil;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午2:12:39 
 * TODO:已拥有称号列表
 */
public class ListCommand extends BaseCommand{

	public ListCommand() {
		super("list", BaseCommandSenderType.PLAYER, 0);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_LIST.name(), LanguageEnum.COMMAND_USEAGE_LIST.getValue()));
		this.setHasPermissions(Arrays.asList(PermissionEnum.PREFIX_USE.getValue(),PermissionEnum.PREFIX_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {
		if(commandSender instanceof Player) {
			Player player = (Player) commandSender;
			
			List<String> prefixList = PrefixServiceImpls.getInstance().getPlayerPrefixs(player);
			int prefixNum = prefixList == null? 0 : prefixList.size();
			
			//发送标题
			String listMessage = MessageUtil.getMessage(LanguageEnum.MESSAGE_LIST_PREFIX, String.valueOf(prefixNum));
			PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(player, listMessage);
			
			//发送称号列表
			if(prefixList!=null) {
				for(int i=0;i<prefixList.size();i++) {
					String prefix = prefixList.get(i);
					PrefixPluginMain.getInstance().getBaseMessageService().sendMessageByLanguage(player, LanguageEnum.MESSAGE_LIST_PREFIX_DATA.name(), LanguageEnum.MESSAGE_LIST_PREFIX_DATA.getValue(), String.valueOf(i+1),prefix);
				}
			}
			
			
		}
	}

}

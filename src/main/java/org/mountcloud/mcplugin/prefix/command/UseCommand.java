package org.mountcloud.mcplugin.prefix.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;
import org.mountcloud.mcplugin.prefix.util.MessageUtil;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午7:34:40 
 * TODO:
 */
public class UseCommand extends BaseCommand{

	public UseCommand() {
		super("use", BaseCommandSenderType.PLAYER, 1);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_USE.name(), LanguageEnum.COMMAND_USEAGE_USE.getValue()));
		this.setHasPermissions(Arrays.asList(PermissionEnum.PREFIX_USE.getValue(),PermissionEnum.PREFIX_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {
		String indexStr = args[0];
		Integer index = null;
		try {
			index = Integer.parseInt(indexStr);
		}catch (Exception e) {
		}
		if(index == null) {
			MessageUtil.sendMessage(commandSender, BaseLanguageEnum.COMMAND_ARGS_ERROR);
			PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(commandSender, getUsage());
			return;
		}
		Player player = (Player) commandSender;
		int result = PrefixServiceImpls.getInstance().usePrefix(player, index);
		if(result==0) {
			String prefix = PrefixServiceImpls.getInstance().getPlayerUsePrefix(player);
			MessageUtil.sendMessage(commandSender, LanguageEnum.MESSAGE_USE_PREFIX,prefix);
		}else if(result==2){
			MessageUtil.sendMessage(commandSender, LanguageEnum.MESSAGE_GIVE_PREFIX_NOT_EXIST,String.valueOf(index));
		}else {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_OPERATION_ERROR);
		}
	}

}

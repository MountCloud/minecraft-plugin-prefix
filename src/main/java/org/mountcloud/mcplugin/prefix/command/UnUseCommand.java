package org.mountcloud.mcplugin.prefix.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;
import org.mountcloud.mcplugin.prefix.util.MessageUtil;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午8:33:57 
 * TODO:卸下称号
 */
public class UnUseCommand extends BaseCommand{

	public UnUseCommand() {
		super("unuse", BaseCommandSenderType.PLAYER, 0);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_UNUSE.name(), LanguageEnum.COMMAND_USEAGE_USE.getValue()));
		this.setHasPermissions(Arrays.asList(PermissionEnum.PREFIX_USE.getValue(),PermissionEnum.PREFIX_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {
		Player player = (Player) commandSender;
		int result = PrefixServiceImpls.getInstance().unUsePrefix(player);
		if(result==0) {
			MessageUtil.sendMessage(commandSender, LanguageEnum.MESSAGE_UNUSE_PREFIX);
		}else {
			MessageUtil.sendMessage(commandSender, LanguageEnum.MESSAGE_OPERATION_ERROR);
		}
	}

}

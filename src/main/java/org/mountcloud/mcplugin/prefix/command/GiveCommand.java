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
 * @version 创建时间：2018年10月13日 下午2:49:12 
 * TODO:给予称号命令
 */
public class GiveCommand extends BaseCommand{

	public GiveCommand() {
		super("give", BaseCommandSenderType.ARBITRARLIY, 2);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_GIVE.name(), LanguageEnum.COMMAND_USEAGE_GIVE.getValue()));
		this.setHasPermissions(Arrays.asList(PermissionEnum.PREFIX_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {
		String nikname = args[0];
		String prefix = args[1];
		
		Player player = PrefixPluginMain.getInstance().getOnlinePlayerByName(nikname);
		if(player == null) {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_PLAYER_NOTFOUND, nikname);
			return;
		}
		int result = PrefixServiceImpls.getInstance().addPrefix(player, prefix);
		
		if(result==0) {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_GIVE_PREFIX, nikname,prefix);
		}else if(result == -2) {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_GIVE_PREFIX_EXIST, nikname,prefix);
		}else {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_OPERATION_ERROR);
		}
		
	}

}

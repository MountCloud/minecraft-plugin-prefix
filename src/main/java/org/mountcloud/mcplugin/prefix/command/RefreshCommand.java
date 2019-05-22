package org.mountcloud.mcplugin.prefix.command;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.config.PlayerPrefixConfig;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.permission.PermissionEnum;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;
import org.mountcloud.mcplugin.prefix.util.MessageUtil;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 下午11:59:54 
 * TODO:刷新我的称号显示命令
 */
public class RefreshCommand extends BaseCommand{

	public RefreshCommand() {
		super("refresh", BaseCommandSenderType.PLAYER, 0);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_REFRESH.name(), LanguageEnum.COMMAND_USEAGE_REFRESH.getValue()));
		this.setHasPermissions(Arrays.asList(PermissionEnum.PREFIX_USE.getValue(),PermissionEnum.PREFIX_ADMIN.getValue()));
	}

	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType commandSenderType) {
		
		if(commandSender instanceof Player) {
			Player player = (Player) commandSender;
			String uuid = player.getUniqueId().toString();
			
			//createUserConfig
			PlayerPrefixConfig playerprefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
			PrefixServiceImpls.getInstance().refreshPrefix(player, playerprefixConfig);
			
			PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(player,MessageUtil.getMessage(LanguageEnum.MESSAGE_REFRESH_PREFIX));
		}
		
	}

}

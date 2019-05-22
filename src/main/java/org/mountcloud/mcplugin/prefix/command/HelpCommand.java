package org.mountcloud.mcplugin.prefix.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.service.language.BaseLanguageService;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.service.PrefixCommandServie;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:59:07 
 * TODO:帮助命令
 */
public class HelpCommand extends BaseCommand{

	public HelpCommand() {
		super("help", BaseCommandSenderType.ARBITRARLIY, 0);
		setUsage(PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.COMMAND_USEAGE_HELP.name(), LanguageEnum.COMMAND_USEAGE_HELP.getValue()));
	}
	
	public static void showHelp(CommandSender sender) {
		String pluginTitle = PrefixPluginMain.getInstance().getBaseLanguageService().getLanguage(LanguageEnum.PLUGIN_TITLE.name(), LanguageEnum.PLUGIN_TITLE.getValue());
		PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(sender, "&5[------------ &9"+pluginTitle+" &5------------]");

		List<String> helps = new ArrayList<String>();
		
		Map<String, BaseCommand> cmds = PrefixCommandServie.getInstance().getCommands();
		Set<String> keys = cmds.keySet();
		for(String key : keys) {
			BaseCommand baseCommand = cmds.get(key);
			if(PrefixCommandServie.getInstance().checkPermission(sender, baseCommand)&&PrefixCommandServie.getInstance().checkCommandSenderType(sender, baseCommand)) {
				String uesAge = baseCommand.getUsage();
				if(!helps.contains(uesAge)) {
					helps.add(uesAge);
				}
			}
		}
		
		for(String help : helps) {
			PrefixPluginMain.getInstance().getBaseMessageService().sendMessage(sender, help);
		}
		
		
	}
	
	@Override
	public void run(CommandSender commandSender, String[] args, BaseCommandSenderType cs) {
		HelpCommand.showHelp(commandSender);
	}

}

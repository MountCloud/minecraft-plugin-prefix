package org.mountcloud.mcplugin.prefix.service;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.mountcloud.mcplugin.common.command.BaseCommand;
import org.mountcloud.mcplugin.common.command.BaseCommandSenderType;
import org.mountcloud.mcplugin.common.language.BaseLanguageEnum;
import org.mountcloud.mcplugin.common.service.command.BaseCommandeSercvice;
import org.mountcloud.mcplugin.common.service.message.BaseMessageService;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.command.HelpCommand;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:54:55 
 * TODO:
 */
public class PrefixCommandServie extends BaseCommandeSercvice{
	
	public PrefixCommandServie(String execCommand) {
		super(execCommand,PrefixPluginMain.getInstance());
	}

	private static PrefixCommandServie prefixCommandServie;
	
	public static PrefixCommandServie getInstance() {
		if(prefixCommandServie == null) {
			prefixCommandServie = new PrefixCommandServie("prefix");
		}
		return prefixCommandServie;
	}

	@Override
	public boolean execCommand(CommandSender sender, BaseCommand command, String[] args,BaseCommandSenderType commandSenderType) {
		if(command instanceof HelpCommand) {
			if(args.length>0) {
				BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_ARGS_ERROR;
				PrefixPluginMain.getInstance().getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue(),command.getCommand());
			}
		}
		command.run(sender, args, commandSenderType);
		return true;
	}

	@Override
	public boolean notFoundCommand(CommandSender sender, Command command, String[] args) {
		BaseLanguageEnum languageEnum = BaseLanguageEnum.COMMAND_NOT_FOUND_COMMAND;
		PrefixPluginMain.getInstance().getBaseMessageService().sendMessageByLanguage(sender, languageEnum.name(), languageEnum.getValue(),command.getName(),getCommandName(args));
		HelpCommand.showHelp(sender);
		return true;
	}

}

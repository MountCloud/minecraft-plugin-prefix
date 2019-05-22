package org.mountcloud.mcplugin.prefix;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.common.config.BaseLanguageConfig;
import org.mountcloud.mcplugin.common.service.config.BaseConfigService;
import org.mountcloud.mcplugin.common.service.log.BaseLogService;
import org.mountcloud.mcplugin.prefix.command.GiveCommand;
import org.mountcloud.mcplugin.prefix.command.HelpCommand;
import org.mountcloud.mcplugin.prefix.command.ListCommand;
import org.mountcloud.mcplugin.prefix.command.RefreshCommand;
import org.mountcloud.mcplugin.prefix.command.UnUseCommand;
import org.mountcloud.mcplugin.prefix.command.UseCommand;
import org.mountcloud.mcplugin.prefix.config.DefaultConfig;
import org.mountcloud.mcplugin.prefix.listener.PlayerListener;
import org.mountcloud.mcplugin.prefix.service.PrefixCommandServie;

import net.milkbowl.vault.chat.Chat;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;
import org.mountcloud.mcplugin.prefixapi.service.PrefixServcie;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:11:35 
 * TODO:
 */
public class PrefixPluginMain extends BasePlugin{
	
	private Chat chat;
	
	/**
	 * 主函数
	 */
	private static PrefixPluginMain prefixPluginMain;
	
	public static PrefixPluginMain getInstance() {
		return prefixPluginMain;
	}

	@Override
	public boolean enable() {
		
		prefixPluginMain = this;
		
		//初始化配置
		this.getBaseLogService().info("init vault chat...");
		setupChat();
		
		//初始化配置
		initConfig();
		//设置监听
		this.getBaseLogService().info("init listener...");
		initListener();
		
		//设置命令
		this.getBaseLogService().info("init command...");
		initCommand();

		//注册服务
		this.getBaseLogService().info("regist command...");
		registService();
		return true;
	}
	
	/**
	 * 注册事件与监听
	 */
	private void initListener() {
		this.registerListener(new PlayerListener());
	}

	/**
	 * 初始化command
	 */
	private void initCommand() {
		//实例化prefix命令的服务
		PrefixCommandServie commandServie = PrefixCommandServie.getInstance();
		
		//实例化 prefix命令下的子命令
		GiveCommand giveCommand = new GiveCommand();
		commandServie.registerCommand(giveCommand);
		
		ListCommand listCommand = new ListCommand();
		commandServie.registerCommand(listCommand);
		
		UseCommand useCommand = new UseCommand();
		commandServie.registerCommand(useCommand);
		
		UnUseCommand unUseCommand = new UnUseCommand();
		commandServie.registerCommand(unUseCommand);
		
		RefreshCommand refreshCommand = new RefreshCommand();
		commandServie.registerCommand(refreshCommand);
		
		
		HelpCommand helpCommand = new HelpCommand();
		commandServie.registerCommand(helpCommand);
		commandServie.registerCommand("", helpCommand);
		
		this.registerCommand(commandServie);
	}
	
	/**
	 * 初始化配置
	 */
	private void initConfig() {
		BaseLanguageConfig languageConfig = new BaseLanguageConfig(this);
		this.getBaseConfigService().registerConfig(languageConfig);
		
		DefaultConfig defaultConfig = new DefaultConfig(getInstance());
		this.getBaseConfigService().registerConfig(defaultConfig);
	}
	
	private void setupChat()
	{
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = (Chat)rsp.getProvider();
	}
	
	public Chat getChat() {
		return chat;
	}


	private void registService(){
		PrefixServiceImpls.getInstance();
	}
}

package org.mountcloud.mcplugin.prefix.service;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.mountcloud.mcplugin.common.BasePlugin;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.config.DefaultConfig;
import org.mountcloud.mcplugin.prefix.config.PlayerPrefixConfig;
import org.mountcloud.mcplugin.prefix.language.LanguageEnum;
import org.mountcloud.mcplugin.prefix.util.MessageUtil;
import org.mountcloud.mcplugin.prefixapi.service.PrefixServcie;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:19:11 
 * TODO:称号服务
 */
public class PrefixServiceImpls implements PrefixServcie{
	
	private static PrefixServiceImpls prefixService;
	
	private PrefixServiceImpls() {
	}
	
	public static PrefixServiceImpls getInstance() {
		if(prefixService == null) {
			prefixService = new PrefixServiceImpls();
			PrefixServcie tempPrefixService = prefixService;
			PrefixPluginMain.getInstance().registerService(PrefixServcie.class,prefixService);
		}
		return prefixService;
	}

	@Override
	public int addPrefix(Player player, String prefix) {
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		List<String> prefixs = playerPrefixConfig.getprefix();
		if(prefixs==null) {
			prefixs = new ArrayList<String>();
		}
		if(prefixs.contains(prefix)) {
			return -2;
		}
		
		prefixs.add(prefix);

		playerPrefixConfig.setprefix(prefixs);
		playerPrefixConfig.saveConfig();
		return 0;
	}

	@Override
	public int removePrefix(Player player, String prefix) {
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		List<String> prefixs = playerPrefixConfig.getprefix();
		if(prefixs==null) {
			prefixs = new ArrayList<String>();
		}
		if(!prefixs.contains(prefix)) {
			return -2;
		}
		
		prefixs.remove(prefixs.indexOf(prefix));

		playerPrefixConfig.setprefix(prefixs);
		playerPrefixConfig.saveConfig();
		return 0;
	}

	@Override
	public int setNickNameColor(Player player, String color) {
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		playerPrefixConfig.setNickColor(color);
		playerPrefixConfig.saveConfig();
		return 0;
	}

	@Override
	public List<String> getPlayerPrefixs(Player player) {
		
		List<String> prefixs = null;
		
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return null;
		}
		
		prefixs = playerPrefixConfig.getprefix();
		
		return prefixs;
	}

	@Override
	public String getPlayerUsePrefix(Player player) {
		String useprefix = null;
		
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return useprefix;
		}
		useprefix = playerPrefixConfig.getUseprefix();
		return useprefix;
	}

	@Override
	public String getPlayUseNikColor(Player player) {
		String nickColor = null;
		
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return nickColor;
		}
		nickColor = playerPrefixConfig.getNickColor();
		return nickColor;
	}

	@Override
	public int usePrefix(Player player,int index) {
		List<String> prefixs = null;
		
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		prefixs = playerPrefixConfig.getprefix();
		
		if(index<1||prefixs.size()<index) {
			return -2;
		}
		
		String prefix = prefixs.get(index-1);
		playerPrefixConfig.setUseprefix(prefix);
		playerPrefixConfig.saveConfig();
		
		refreshPrefix(player,playerPrefixConfig);
		return 0;
	}

	@Override
	public int setPlayerNickColor(Player player, String color) {
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		playerPrefixConfig.setNickColor(color);
		playerPrefixConfig.saveConfig();

		refreshPrefix(player,playerPrefixConfig);
		return 0;
	}
	
	@Override
	public int unUsePrefix(Player player) {
		String uuid = player.getUniqueId().toString();
		PlayerPrefixConfig playerPrefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(!playerPrefixConfig.isInit()) {
			return -1;
		}
		playerPrefixConfig.setUseprefix("");
		playerPrefixConfig.saveConfig();
		refreshPrefix(player, playerPrefixConfig);
		return 0;
	}
	
	/**
	 * 刷新玩家信息
	 * @param player 玩家
	 * @param config 配置
	 */
	public void refreshPrefix(Player player,PlayerPrefixConfig config) {
		String prefix = config.getUseprefix();
		String nickNameColor = config.getNickColor();
		
		if(prefix == null) {
			prefix = "";
		}
		if(nickNameColor==null) {
			nickNameColor = "&c";
		}
		
		String framColor = "";
		
		DefaultConfig defaultConfig=PrefixPluginMain.getInstance().getBaseConfigService().getConfig(DefaultConfig.configName);
		
		if(defaultConfig!=null) {
			framColor = defaultConfig.getprefixFrameColor();
		}
		
		String fullPrefix = null;
		if(prefix.equals("")) {
			fullPrefix = nickNameColor;
		}else {
			fullPrefix = framColor+"["+prefix+framColor+"]"+nickNameColor;
		}
		
		String colorPrefix = ChatColor.translateAlternateColorCodes('&', fullPrefix);
		
		BasePlugin plugin = PrefixPluginMain.getInstance();
		if(plugin instanceof PrefixPluginMain) {
			PrefixPluginMain prefixPluginMain = (PrefixPluginMain) plugin;
			prefixPluginMain.getChat().setPlayerPrefix(player, colorPrefix);
		}else {
			MessageUtil.sendMessage(player, LanguageEnum.MESSAGE_OPERATION_ERROR);
		}
	}

	
	
}

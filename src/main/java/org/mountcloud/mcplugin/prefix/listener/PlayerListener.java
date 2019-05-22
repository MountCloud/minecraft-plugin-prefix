package org.mountcloud.mcplugin.prefix.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.mountcloud.mcplugin.common.listener.BaseListener;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;
import org.mountcloud.mcplugin.prefix.config.PlayerPrefixConfig;
import org.mountcloud.mcplugin.prefix.service.PrefixServiceImpls;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:39:01 
 * TODO:用户事件监听
 */
public class PlayerListener extends BaseListener{

	@EventHandler
	public void onPlayJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		String uuid = player.getUniqueId().toString();
		
		//createUserConfig
		PlayerPrefixConfig playerprefixConfig = new PlayerPrefixConfig(PlayerPrefixConfig.getConfigName(uuid),PrefixPluginMain.getInstance());
		if(playerprefixConfig.isInit()) {
			PrefixServiceImpls.getInstance().refreshPrefix(player, playerprefixConfig);
		}
		
	}
	
}

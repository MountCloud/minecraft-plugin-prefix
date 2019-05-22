package org.mountcloud.mcplugin.prefix.config;

import org.mountcloud.mcplugin.common.config.BaseConfig;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月13日 下午2:33:45 
 * TODO:默认配置
 */
public class DefaultConfig extends BaseConfig{
	
	public static String configName = "config.yml";
	private String prefixFrameColor;

	public DefaultConfig(PrefixPluginMain prefixPluginMain) {
		super(configName,prefixPluginMain);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean createConfig() {
		PrefixPluginMain.getInstance().getConfig();
		return true;
	}

	@Override
	public void loadConfig() {
		prefixFrameColor = this.getConfig().getString("prefix.prefixFrameColor","");
	}

	public String getprefixFrameColor() {
		setProperties("prefix.prefixFrameColor", prefixFrameColor);
		return prefixFrameColor;
	}

	public void setprefixFrameColor(String prefixFrameColor) {
		this.prefixFrameColor = prefixFrameColor;
	}
	
	

}

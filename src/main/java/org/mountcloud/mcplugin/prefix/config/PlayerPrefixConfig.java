package org.mountcloud.mcplugin.prefix.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mountcloud.mcplugin.common.config.BaseConfig;
import org.mountcloud.mcplugin.prefix.PrefixPluginMain;

/** 
 * @author zhanghaishan 
 * @version 创建时间：2018年10月12日 上午12:18:47 
 * TODO:用户配置文件
 */
public class PlayerPrefixConfig extends BaseConfig{
	
	private List<String> prefix;
	private String useprefix;
	private String nickColor;
	

	@Override
	protected boolean createConfig() {
		boolean state = false;
		
		if(!this.createNewConfigFile()) {
			return false;
		}
		
		this.getConfig().set("user.prefix.useprefix", "");
		this.getConfig().set("user.prefix.nickColor", "&c");
		this.getConfig().set("user.prefix.prefix", new ArrayList<String>());

		try {
			this.getConfig().save(getFile());
			state = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	
	/**
	 * 返回用户的配置文件名称
	 * @param uuid
	 * @return
	 */
	public static String getConfigName(String uuid) {
		String fileName = "player/"+uuid+".yml";
		return fileName;
	}
	
	public PlayerPrefixConfig(String fileName,PrefixPluginMain prefixPluginMain) {
		super(fileName,prefixPluginMain);
	}

	@Override
	public void loadConfig() {
		this.setUseprefix(getConfig().getString("user.prefix.useprefix"));
		this.setNickColor(getConfig().getString("user.prefix.nickColor"));
		List<?> userprefixs = getConfig().getList("user.prefix.prefix");
		if(userprefixs!=null) {
			this.setprefix(new ArrayList<String>());
			for(int i=0;i<userprefixs.size();i++) {
				String tempprefix = userprefixs.get(i).toString();
				this.getprefix().add(tempprefix);
			}
		}
	}

	public List<String> getprefix() {
		return prefix;
	}

	public void setprefix(List<String> prefix) {
		setProperties("user.prefix.prefix", prefix);
		this.prefix = prefix;
	}

	public String getUseprefix() {
		return useprefix;
	}

	public void setUseprefix(String useprefix) {
		setProperties("user.prefix.useprefix", useprefix);
		this.useprefix = useprefix;
	}

	public String getNickColor() {
		return nickColor;
	}

	public void setNickColor(String nickColor) {
		setProperties("user.prefix.nickColor", nickColor);
		this.nickColor = nickColor;
	}

	

}

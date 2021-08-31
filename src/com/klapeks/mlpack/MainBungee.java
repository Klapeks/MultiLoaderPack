package com.klapeks.mlpack;

import com.klapeks.coserver.IMLPack;

import net.md_5.bungee.api.plugin.Plugin;

public class MainBungee extends Plugin {
	
	static IMLPack<Plugin> coserver = new com.klapeks.coserver.plugin.bungee.MLPack();
	static IMLPack<Plugin> mlwd = new com.klapeks.mlwd.bungee.MLPack();
	static IMLPack<Plugin> mlpd = new com.klapeks.mlpd.bungee.MLPack();
	
	public MainBungee() {
		coserver.init(this);
		mlwd.init(this);
		mlpd.init(this);
	}
	
	@Override
	public void onLoad() {
		coserver.load(this);
		mlwd.load(this);
		mlpd.load(this);
	}
	
	@Override
	public void onEnable() {
		coserver.enable(this);
		mlwd.enable(this);
		mlpd.enable(this);
	}
	
	@Override
	public void onDisable() {
		mlpd.disable(this);
		mlwd.disable(this);
		coserver.disable(this);
	}
	
}

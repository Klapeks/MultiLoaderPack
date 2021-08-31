package com.klapeks.mlpack;

import java.io.File;
import java.io.FileWriter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.klapeks.coserver.IMLPack;

public class MainBukkit extends JavaPlugin {

	static IMLPack<JavaPlugin> coserver = new com.klapeks.coserver.plugin.bukkit.MLPack();
	static IMLPack<JavaPlugin> mlpd = new com.klapeks.mlpd.bukkit.MLPack();
	static IMLPack<JavaPlugin> mlwd = new com.klapeks.mlwd.bukkit.MLPack();

	static boolean p,w;
	public MainBukkit() {
		p = true;
		w = true;
		try {
			File file = new File(getDataFolder() + File.separator + "loader.yml");
			if (!file.exists()) {
				file.getParentFile().mkdirs();
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				fw.write("# Does it need to load and enable MultiLoaderPluginDownloader" + "\n"
						+ "MLPD: true" + "\n\n"
						+ "# Does it need to load and enable MultiLoaderWorldDownloader" + "\n"
						+ "MLWD: true");
				fw.flush();
				fw.close();
			}
			FileConfiguration fc = YamlConfiguration.loadConfiguration(file);
			p = fc.contains("MLPD") && fc.getBoolean("MLPD");
			w = fc.contains("MLWD") && fc.getBoolean("MLWD");
		} catch (Throwable t) {
			t.printStackTrace();
		}
		coserver.init(this);
		if (w) mlwd.init(this);
		if (p) mlpd.init(this);
	}
	
	@Override
	public void onLoad() {
		coserver.load(this);
		if (w) mlwd.load(this);
		if (p) mlpd.load(this);
	}
	
	@Override
	public void onEnable() {
		coserver.enable(this);
		if (w) mlwd.enable(this);
		if (p) mlpd.enable(this);
	}
	
	@Override
	public void onDisable() {
		coserver.disable(this);
		if (w) mlwd.disable(this);
		if (p) mlpd.disable(this);
	}
}

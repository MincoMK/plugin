package com.minco.plugin.ccs;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;

public class TestExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		String j = StringUtils.join(args, ' ');
		j = j.replace("/ccs ", "");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say Hello, world!");
		return true;
	}
}

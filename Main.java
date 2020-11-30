package com.minco.plugin.ccs;

import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Main extends JavaPlugin {
	public void onEnable() {
			ss();
			
			getCommand("ccs").setExecutor(new CommandExecutor() {
				@Override
				public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
					Player p = (Player) sender;
					String j = StringUtils.join(args, ' ');
					j = j.replace("/ccs ", "");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say Hello, world!");
					return true;
				}
			});
	}
	
	/**
	 * 
	 * This method opens Server!
	 * @param s Socket
	 */
	private static void openServer(Socket s) {
		try {
			InputStream in = s.getInputStream();
			OutputStream out = s.getOutputStream();
			
			Scanner scanner = new Scanner(in, "UTF-8");
			PrintWriter print = new PrintWriter(new OutputStreamWriter(out, "UTF-8"), true);
			
			print.println("Welcome to Remote Controllable Console Command Sender Software By Minco!");
			
			boolean done = false;
			while(!done && scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    print.println("Command to run: "+line);
			    Bukkit.broadcastMessage(line);
			    
			}
		} catch (IOException e) {
			
		}
		
		
		
	}
	
	private static void ss() {
		try {
			final ServerSocket ss = new ServerSocket(9991);
			Runnable r = new Runnable() {
				public void run() {
					while(true) {
						try {
							Socket s = ss.accept();
							if (!(s == null)) openServer(s);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				}
			};
			
			Thread t = new Thread(r);
			t.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}





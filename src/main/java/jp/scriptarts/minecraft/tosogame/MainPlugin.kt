package jp.scriptarts.minecraft.tosogame

import jp.scriptarts.minecraft.tosogame.commands.AdminCommand
import jp.scriptarts.minecraft.tosogame.listeners.HunterEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MainPlugin: JavaPlugin() {
    override fun onEnable() {
        getCommand("toso")?.setExecutor(AdminCommand)
        Bukkit.getPluginManager().registerEvents(HunterEvent, this)
    }
}

package jp.scriptarts.minecraft.tosogame

import jp.scriptarts.minecraft.tosogame.commands.AdminCommand
import jp.scriptarts.minecraft.tosogame.listeners.HunterEvent
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class MainPlugin: JavaPlugin() {
    override fun onEnable() {
        // register command
        getCommand("toso")?.setExecutor(AdminCommand)

        // register events
        Bukkit.getPluginManager().registerEvents(HunterEvent, this)
    }
}

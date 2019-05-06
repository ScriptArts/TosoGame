package jp.scriptarts.minecraft.tosogame.listeners

import jp.scriptarts.minecraft.tosogame.MainPlugin
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta
import org.bukkit.scoreboard.DisplaySlot

object GeneralListener : Listener {
    @EventHandler
    fun onJoin(e: PlayerLoginEvent) {
        MainPlugin.getScoreBoard()?.let {
            e.player.scoreboard = it
        }
    }
}

package jp.scriptarts.minecraft.tosogame.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

/**
 *
 */
object HunterEvent: Listener {
    @EventHandler
    public fun onMove(e: PlayerMoveEvent) {
        println(e.player.name)
    }
}

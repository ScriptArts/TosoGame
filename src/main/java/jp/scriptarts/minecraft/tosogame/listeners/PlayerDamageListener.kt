package jp.scriptarts.minecraft.tosogame.listeners

import jp.scriptarts.minecraft.tosogame.MainPlugin
import jp.scriptarts.minecraft.tosogame.events.HunterTouchPlayerEvent
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent

/**
 *
 */
class PlayerDamageListener : Listener {
    @EventHandler
    fun onDamage(e: EntityDamageByEntityEvent) {
        val player = e.entity
        val hunter = e.damager
        if (player !is Player && hunter !is Player) {
            return
        }

        val scoreBoard = MainPlugin.getScoreBoard() ?: return

        val playerTeam = scoreBoard.getTeam("player") ?: return
        val hunterTeam = scoreBoard.getTeam("hunter") ?: return

        if (!playerTeam.hasEntry(player.name) || !hunterTeam.hasEntry(hunter.name)) {
            return
        }

        val event = HunterTouchPlayerEvent(hunter as Player, player as Player)
        Bukkit.getPluginManager().callEvent(event)
        e.damage = 0.0
        e.isCancelled = event.cancel
    }
}

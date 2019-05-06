package jp.scriptarts.minecraft.tosogame.listeners

import jp.scriptarts.minecraft.tosogame.events.HunterTouchPlayerEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class HunterListener: Listener {
    @EventHandler
    fun onHunterTouchPlayerEvent(e: HunterTouchPlayerEvent) {
        println("${e.hunter.name}, ${e.player.name}")
    }
}

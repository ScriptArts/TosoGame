package jp.scriptarts.minecraft.tosogame.listeners

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

/**
 * ハンターイベント（サンプル）
 */
object HunterEvent: Listener {
    /**
     * ハンターが動いたとき（サンプルなので wip）
     */
    @EventHandler
    fun onMove(e: PlayerMoveEvent) {
        println(e.player.name)
    }
}

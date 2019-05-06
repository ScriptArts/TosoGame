package jp.scriptarts.minecraft.tosogame.events

import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class HunterTouchPlayerEvent(val hunter: Player, val player: Player) : Event(), Cancellable {
    var cancel: Boolean = false

    companion object {
        private val HANDLERS = HandlerList()

        //I just added this.
        @JvmStatic
        fun getHandlerList(): HandlerList {
            return HANDLERS
        }
    }

    override fun setCancelled(cancel: Boolean) {
        this.cancel = cancel
    }

    override fun isCancelled(): Boolean {
        return this.cancel
    }

    override fun getHandlers(): HandlerList {
        return HANDLERS
    }
}

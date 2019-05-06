package jp.scriptarts.minecraft.tosogame

import jp.scriptarts.minecraft.tosogame.commands.admin.AdminCommand
import jp.scriptarts.minecraft.tosogame.listeners.GeneralListener
import jp.scriptarts.minecraft.tosogame.listeners.HunterListener
import jp.scriptarts.minecraft.tosogame.listeners.PlayerDamageListener
import jp.scriptarts.minecraft.tosogame.utils.GameStatus
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scoreboard.Scoreboard

class MainPlugin : JavaPlugin() {
    companion object {
        private var Scoreboard: Scoreboard? = null
        var Status: GameStatus = GameStatus.BEFORE_GAME

        fun getScoreBoard(): Scoreboard? {
            return Scoreboard
        }
    }

    override fun onEnable() {
        getCommand("tosoadmin")?.setExecutor(AdminCommand(this))
        Bukkit.getPluginManager().registerEvents(GeneralListener, this)
        Bukkit.getPluginManager().registerEvents(PlayerDamageListener(), this)
        Bukkit.getPluginManager().registerEvents(HunterListener(), this)

        config.options().copyDefaults(true)
        saveDefaultConfig()

        initScoreBoard()
    }

    private fun initScoreBoard() {
        Bukkit.getServer().scoreboardManager?.newScoreboard?.let { it ->
            it.registerNewTeam("hunter").let {
                it.displayName = "ハンター"
                it.color = ChatColor.RED
            }

            it.registerNewTeam("player").let {
                it.displayName = "プレイヤー"
                it.color = ChatColor.WHITE
            }

            it.registerNewTeam("admin").let {
                it.displayName = "管理者"
                it.color = ChatColor.YELLOW
            }

            Scoreboard = it
        }
    }
}

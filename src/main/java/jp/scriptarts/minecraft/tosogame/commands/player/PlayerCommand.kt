package jp.scriptarts.minecraft.tosogame.commands.player

import jp.scriptarts.minecraft.tosogame.MainPlugin
import jp.scriptarts.minecraft.tosogame.utils.GameStatus
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.security.SecureRandom

class PlayerCommand(javaPlugin: JavaPlugin) : CommandExecutor {
    private val plugin: JavaPlugin = javaPlugin

    /**
     * Sample command
     * /tosoadmin
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
//            return help(sender)
        }

        when (args[0]) {
            "dice" -> return dice(sender)
        }

        return false
    }

    fun dice(sender: CommandSender): Boolean {
        val instance = SecureRandom.getInstance("SHA1PRNG")
        val result = instance.nextInt(100)

        if (sender !is Player) {
            sender.sendMessage("$result がでた。")
            return true
        }

        val team = MainPlugin.getScoreBoard()?.getTeam("player")
        if (team == null || team.name != "player") {
            sender.sendMessage("$result がでた。")
            return true
        }

        if (MainPlugin.Status == GameStatus.OP_GAME) {
        }

        return false
    }
}

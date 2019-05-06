package jp.scriptarts.minecraft.tosogame.commands.admin

import jp.scriptarts.minecraft.tosogame.MainPlugin
import jp.scriptarts.minecraft.tosogame.utils.GameStatus
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object GameCommand {
    fun status(sender: CommandSender): Boolean {
        val status = MainPlugin.Status
        sender.sendMessage("ゲームステータス：${status.name}")
        return true
    }

    fun startOpGame(sender: CommandSender): Boolean {
        val status = MainPlugin.Status
        if (status != GameStatus.BEFORE_GAME) {
            sender.sendMessage("ゲームが進行中もしくは終了しています。リセットコマンドでリセットしてください。")
            return false
        }

        MainPlugin.Status = GameStatus.OP_GAME

        return true
    }

    fun startGame(sender: CommandSender): Boolean {
        val status = MainPlugin.Status
        if (status != GameStatus.BEFORE_GAME || status != GameStatus.OP_GAME) {
            sender.sendMessage("ゲームが進行中もしくは終了しています。リセットコマンドでリセットしてください。")
            return false
        }

        MainPlugin.Status = GameStatus.IN_GAME

        return true
    }

    fun reset(sender: CommandSender): Boolean {
        MainPlugin.Status = GameStatus.BEFORE_GAME
        MainPlugin.getScoreBoard()?.also { board ->
            board.teams.forEach { team ->
                team.entries.forEach { entry ->
                    team.removeEntry(entry)
                }
            }
        }

        sender.sendMessage("ゲームをリセットしました")

        return true
    }
}

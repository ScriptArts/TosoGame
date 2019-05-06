package jp.scriptarts.minecraft.tosogame.commands.admin

import jp.scriptarts.minecraft.tosogame.MainPlugin
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object PlayerCommand {
    fun list(sender: CommandSender): Boolean {
        val scoreBoard = MainPlugin.getScoreBoard() ?: return false
        val player = scoreBoard.getTeam("player") ?: return false

        val messageList = arrayListOf("--- 逃走者リスト ---")
        player.entries.forEach { messageList.add("$it \n") }
        messageList.add("--------------------")

        sender.sendMessage(messageList.toTypedArray())

        return true
    }

    fun add(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("プレイヤー名を入力してください")
            return false
        }

        val player = Bukkit.getServer().getPlayer(args[2]) ?: return false
        val scoreBoard = MainPlugin.getScoreBoard() ?: return false

        scoreBoard.getEntryTeam(player.name)?.also {
            if (it.name == "player") return true
            it.removeEntry(player.name)
            return false
        }

        scoreBoard.getTeam("player")?.addEntry(player.name)

        sender.sendMessage("${player.name} を逃走者に追加しました")

        return true
    }

    fun delete(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("プレイヤー名を入力してください")
            return false
        }

        val player = Bukkit.getServer().getPlayer(args[2]) ?: return false
        val scoreBoard = MainPlugin.getScoreBoard() ?: return false

        val playerTeam = scoreBoard.getTeam("player") ?: return false
        if (!playerTeam.hasEntry(player.name)) {
            sender.sendMessage("${player.name} は逃走者チームに存在しません")
            return false
        }

        playerTeam.removeEntry(player.name)
        sender.sendMessage("${player.name} を逃走者チームから削除しました")

        return true
    }
}

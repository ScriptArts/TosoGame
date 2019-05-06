package jp.scriptarts.minecraft.tosogame.commands.admin

import jp.scriptarts.minecraft.tosogame.MainPlugin
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender

object HunterCommand {
    fun list(sender: CommandSender): Boolean {
        val scoreBoard = MainPlugin.getScoreBoard() ?: return false
        val hunterTeam = scoreBoard.getTeam("hunter") ?: return false

        val messageList = arrayListOf("--- ハンターリスト ---")
        hunterTeam.entries.forEach { messageList.add("$it \n") }
        messageList.add("----------------------")

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
            if (it.name == "hunter") return true
            it.removeEntry(player.name)
            return false
        }

        scoreBoard.getTeam("hunter")?.addEntry(player.name)

        sender.sendMessage("${player.name} をハンターに追加しました")

        return true
    }

    fun delete(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("プレイヤー名を入力してください")
            return false
        }

        val player = Bukkit.getServer().getPlayer(args[2]) ?: return false
        val scoreBoard = MainPlugin.getScoreBoard() ?: return false

        val hunterTeam = scoreBoard.getTeam("hunter") ?: return false
        if (!hunterTeam.hasEntry(player.name)) {
            sender.sendMessage("${player.name} はハンターチームに存在しません")
            return false
        }

        hunterTeam.removeEntry(player.name)
        sender.sendMessage("${player.name} をハンターチームから削除しました")

        return true
    }
}

package jp.scriptarts.minecraft.tosogame.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object AdminCommand: CommandExecutor {
    /**
     * Sample command
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        println("foobar")
        return true
    }
}

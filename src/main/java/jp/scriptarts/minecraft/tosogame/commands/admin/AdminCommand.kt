package jp.scriptarts.minecraft.tosogame.commands.admin

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class AdminCommand(javaPlugin: JavaPlugin) : CommandExecutor {
    private val plugin: JavaPlugin = javaPlugin

    /**
     * Sample command
     * /tosoadmin
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            return help(sender)
        }

        when (args[0]) {
            "hunter" -> {
                return hunter(sender, args)
            }
            "player" -> {
                return player(sender, args)
            }
            "box" -> {
                return box(sender, args)
            }
            "game" -> {
                return game(sender, args)
            }
        }

        return false
    }

    private fun help(sender: CommandSender): Boolean {
        return true
    }

    private fun hunter(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() == 1) {
            return BoxCommand.help(sender)
        }

        when (args[1]) {
            "list" -> return HunterCommand.list(sender)
            "add" -> return HunterCommand.add(sender, args)
            "delete" -> return HunterCommand.delete(sender, args)
        }

        return true
    }

    private fun player(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() == 1) {
            return BoxCommand.help(sender)
        }

        when (args[1]) {
            "list" -> return PlayerCommand.list(sender)
            "add" -> return PlayerCommand.add(sender, args)
            "delete" -> return PlayerCommand.delete(sender, args)
        }

        return true
    }

    /**
     * box list
     * box create
     * box delete
     * box update
     */
    private fun box(sender: CommandSender, args: Array<out String>): Boolean {
        println(args)

        if (args.count() == 1) {
            return BoxCommand.help(sender)
        }

        when (args[1]) {
            "list" -> return BoxCommand.list(sender, plugin)
            "create" -> return BoxCommand.create(sender, args, plugin)
            "open" -> return BoxCommand.open(sender, args, plugin)
            "delete" -> return BoxCommand.delete(sender, args, plugin)
        }

        return false
    }

    private fun game(sender: CommandSender, args: Array<out String>): Boolean {
        if (args.count() == 1) {
            return BoxCommand.help(sender)
        }

        when (args[1]) {
            "status" -> return GameCommand.status(sender)
        }

        return true
    }
}

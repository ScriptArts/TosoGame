package jp.scriptarts.minecraft.tosogame.commands.admin

import org.bukkit.Effect
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

object BoxCommand {
    fun help(sender: CommandSender): Boolean {
        return false
    }

    fun list(sender: CommandSender, plugin: JavaPlugin): Boolean {
        val messages = arrayListOf<String>()
        val box = plugin.config.getMapList("box")
        box.forEach {
            messages.add("BoxName: %s".format(it["name"]))

            val m = it["location"] as Map<String, String>

            messages.add(" X: %s".format(m["x"]))
            messages.add(" Y: %s".format(m["y"]))
            messages.add(" X: %s".format(m["z"]))

            messages.add(" 解放フラグ: %s".format(it["isOpen"]))
        }

        sender.sendMessage(messages.toTypedArray())

        return true
    }

    fun create(sender: CommandSender, args: Array<out String>, plugin: JavaPlugin): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("ボックス名を入力してください")
            return false
        }

        val box = plugin.config.getMapList("box")

        if (sender is Player) {
            val b = sender.getTargetBlock(null, 5)
            val loc = b.location

            val m = hashMapOf<String, Any>()
            m["name"] = args[2]
            m["location"] = mapOf("x" to loc.blockX, "y" to loc.blockY, "z" to loc.blockZ)
            m["isOpen"] = false
            m["world"] = sender.world.uid.toString()
            box.add(m)

            plugin.config.set("box", box)
            plugin.saveConfig()

            return false
        }

        return false
    }

    fun open(sender: CommandSender, args: Array<out String>, plugin: JavaPlugin): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("ボックス名を入力してください")
            return false
        }

        val box = plugin.config.getMapList("box")

        box.forEach { map ->
            if (map["name"] == args[2]) {
                val world = plugin.server.getWorld(UUID.fromString(map["world"] as String))
                world ?: return@forEach

                val loc = map["location"] as Map<String, Any>
                val x = loc["x"] as Int
                val y = loc["y"] as Int
                val z = loc["z"] as Int

                val block = world.getBlockAt(x, y, z)
                val location = block.location

                world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1f)
                world.spawnParticle(Particle.EXPLOSION_NORMAL, location, 20)
                block.type = Material.AIR
            }
        }

        return false
    }

    fun delete(sender: CommandSender, args: Array<out String>, plugin: JavaPlugin): Boolean {
        if (args.count() < 3) {
            sender.sendMessage("ボックス名を入力してください")
            return false
        }

        val box = plugin.config.getMapList("box")
        val isRemove = box.removeIf {
            it["name"] == args[2]
        }

        println(isRemove)

        plugin.config.set("box", box)
        plugin.saveConfig()

        return false
    }
}

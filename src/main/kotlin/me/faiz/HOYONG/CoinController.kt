package me.faiz.HOYONG

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.*

@Suppress("unused")
class CoinController(val plugin: Plugin) : Listener {

    private val file get() = File(plugin.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    operator fun get(uniqueId: UUID): Int {
        return yaml.getInt(uniqueId.toString())
    }

    operator fun set(uniqueId: UUID, coin: Int) {
        yaml.set(uniqueId.toString(), coin)
    }

    operator fun set(player: Player, coin: Int) = set(player.uniqueId, coin)

    operator fun get(player: Player) = get(player.uniqueId)

    fun save() {
        yaml.save(file)
    }



}
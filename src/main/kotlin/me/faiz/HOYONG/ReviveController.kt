package me.faiz.HOYONG

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.*

class ReviveController(val pl:Plugin):Listener {

    private val file get() = File(pl.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    operator fun get(uniqueId: UUID): Int {
        return yaml.getInt("$uniqueId.revive")
    }

//    fun getAd(uniqueId: UUID)

    operator fun set(uniqueId: UUID, count: Int) {
        yaml.set("$uniqueId.revive", count)
    }

    fun getData(){
        yaml.load(file)
    }

    operator fun set(player: Player, count: Int) = set(player.uniqueId, count)

    operator fun get(player: Player) = get(player.uniqueId)

    fun save() {
        yaml.save(file)
    }

}
package me.faiz.HOYONG

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.UUID

class NewbieController(val plugin:Plugin):Listener {

    private val file get() = File(plugin.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    operator fun get(uniqueId:UUID):Long{
        return yaml.getLong("$uniqueId.ftime")
    }

    operator fun set(uniqueId: UUID,mills:Long){
        yaml.set("$uniqueId.ftime",mills)
    }

    fun save() {
        yaml.save(file)
    }

    fun getData(){
        yaml.load(file)
    }

    operator fun get(player: Player):Long = get(player.uniqueId)

    operator fun set(player: Player,mills: Long) = set(player.uniqueId,mills)

}
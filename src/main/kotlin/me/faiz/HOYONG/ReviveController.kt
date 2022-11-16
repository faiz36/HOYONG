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

    fun getSt(uniqueId: UUID): Boolean{
        return yaml.getBoolean("$uniqueId.story")
    }

    fun getNe(uniqueId: UUID): Boolean{
        return yaml.getBoolean("$uniqueId.nether")
    }

    fun getend(uniqueId: UUID): Boolean{
        return yaml.getBoolean("$uniqueId.end")
    }

    fun getAdv(uniqueId: UUID): Boolean{
        return yaml.getBoolean("$uniqueId.adventure")
    }

    fun gethusbandry(uniqueId: UUID): Boolean{
        return yaml.getBoolean("$uniqueId.husbandry")
    }

    fun setSt(uniqueId: UUID,boolean: Boolean){
         yaml.set("$uniqueId.story",boolean)
    }

    fun setNe(uniqueId: UUID,boolean: Boolean){
        yaml.set("$uniqueId.nether",boolean)
    }

    fun setend(uniqueId: UUID,boolean: Boolean){
        yaml.set("$uniqueId.end",boolean)
    }

    fun setAdv(uniqueId: UUID,boolean: Boolean){
        return yaml.set("$uniqueId.adventure",boolean)
    }

    fun sethusbandry(uniqueId: UUID,boolean: Boolean){
        return yaml.set("$uniqueId.husbandry",boolean)
    }

    operator fun set(uniqueId: UUID, count: Int) {
        yaml.set("$uniqueId.revive", count)
    }

    fun getData(){
        yaml.load(file)
    }

    fun getSt(player:Player) = getSt(player.uniqueId)

    fun getNe(player:Player) = getNe(player.uniqueId)

    fun getend(player:Player) = getend(player.uniqueId)

    fun getAdv(player:Player) = getAdv(player.uniqueId)

    fun gethusbandry(player:Player) = gethusbandry(player.uniqueId)

    fun setSt(player:Player,boolean: Boolean) = setSt(player.uniqueId,boolean)

    fun setNe(player:Player,boolean: Boolean) = setNe(player.uniqueId,boolean)

    fun setend(player:Player,boolean: Boolean) = setend(player.uniqueId,boolean)

    fun setAdv(player:Player,boolean: Boolean) = setAdv(player.uniqueId,boolean)

    fun sethusbandry(player:Player,boolean: Boolean) = sethusbandry(player.uniqueId,boolean)


    operator fun set(player: Player, count: Int) = set(player.uniqueId, count)

    operator fun get(player: Player) = get(player.uniqueId)

    fun save() {
        yaml.save(file)
    }

}
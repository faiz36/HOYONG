package me.faiz.HOYONG

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.io.File
import java.util.*

@Suppress("unused")
class ChatController(val plugin:Plugin):Listener {

    private val file get() = File(plugin.dataFolder, "data.yml")
    private val yaml = YamlConfiguration.loadConfiguration(file)

    fun getNick(uniqueId:UUID){
        yaml.getString("$uniqueId.chat.nick")
    }

    fun setNick(uniqueId:UUID,nick:String){
        yaml.set("$uniqueId.chat.nick",nick)
    }

    fun getColor(uniqueId:UUID){
        yaml.getString("$uniqueId.chat.color")
    }

    fun setColor(uniqueId:UUID,color:String){
        yaml.set("$uniqueId.chat.color",color)
    }

    fun getNick(pl:Player) = getNick(pl.uniqueId)

    fun getColor(pl:Player) = getColor(pl.uniqueId)

    fun setNick(pl:Player,nick:String) = setNick(pl.uniqueId,nick)

    fun setColor(pl:Player,color:String) = setColor(pl.uniqueId,color)

    fun getData(){
        yaml.load(file)
    }

    fun save() {
        yaml.save(file)
    }

}
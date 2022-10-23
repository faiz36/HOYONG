package me.faiz.HOYONG

import io.papermc.paper.event.player.AsyncChatEvent
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class HandleChat(val plugin: Plugin,val cclr:ChatController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    @EventHandler
    fun onChat(e:AsyncChatEvent){
        e.isCancelled = true
        println(e.message().toString())
        cclr.getData()
        if(cclr.getNick(e.player) != null){
            if(cclr.getColor(e.player) != null){
                Bukkit.broadcastMessage("${cclr.getNick(e.player)} : ${cclr.getColor(e.player)}${e.message()}")
            }else{
                Bukkit.broadcastMessage("${cclr.getNick(e.player)} : ${e.message()}")
            }
        }else if(cclr.getColor(e.player) != null){
            Bukkit.broadcastMessage("${e.player.name} : ${cclr.getColor(e.player)}${e.message()}")
        }else{
            Bukkit.broadcastMessage("${e.player.name} : ${e.message()}")
        }
    }

}
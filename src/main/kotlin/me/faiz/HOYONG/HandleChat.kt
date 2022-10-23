@file:Suppress("DEPRECATION")
package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.plugin.Plugin


class HandleChat(val plugin: Plugin,val cclr:ChatController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    @EventHandler
    fun onChat(e:PlayerChatEvent){
        e.isCancelled = true
        cclr.getData()
        if(cclr.getNick(e.player) != null){
            if(cclr.getColor(e.player) != null){
                Bukkit.broadcastMessage("${cclr.getNick(e.player)}§r§l :§r ${cclr.getColor(e.player)}}${e.message}")
            }else{
                Bukkit.broadcastMessage("${cclr.getNick(e.player)}§r§l :§r ${e.message}")
            }
        }else if(cclr.getColor(e.player) != null){
            Bukkit.broadcastMessage("${e.player.name}§r§l : §r${cclr.getColor(e.player)}${e.message}")
        }else{
            Bukkit.broadcastMessage("${e.player.name}§r§l : §r${e.message}")
        }
    }

}
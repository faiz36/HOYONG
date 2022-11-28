package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin

class HandleNewbie(val plugin: Plugin,val nclr:NewbieController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    @EventHandler
    fun onJoin(e:PlayerJoinEvent){
        if(nclr[e.player]==0L){
            nclr[e.player] = System.currentTimeMillis()/1000+(15*60)
        }
    }

    @EventHandler
    fun onDamage(e:EntityDamageByEntityEvent){
        if(e.entity.type != EntityType.PLAYER) return
        if(e.damager.type == EntityType.PLAYER){
            if(nclr[e.entity as Player] >= System.currentTimeMillis()/1000){
                e.isCancelled = true
            }
        }
    }

}
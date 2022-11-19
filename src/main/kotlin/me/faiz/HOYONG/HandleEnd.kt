package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.plugin.Plugin

class HandleEnd(val plugin:Plugin):Listener{

    init { Bukkit.getServer().pluginManager.registerEvents(this,plugin) }

    @EventHandler
    fun onBlockDamage(e:EntityDamageByBlockEvent){
        if(e.entityType!= EntityType.PLAYER) return
        if(e.damager?.type == null){
            e.damage = e.damage/5
        }
    }

    @EventHandler
    fun onEntityDamage(e:EntityDamageByEntityEvent){
        if(e.entityType!=EntityType.PLAYER) return
        if(e.damager.type == EntityType.ENDER_CRYSTAL){
            e.damage = e.damage/5
        }
    }

    @EventHandler
    fun onDie(e: EntityDeathEvent){
        if(e.entityType==EntityType.ENDER_DRAGON){
            e.entity.world.getBlockAt(0,65,0).type = Material.DRAGON_EGG
        }
    }

}
package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class HandleRevive(val plugin:Plugin,val rclr: ReviveController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,NewMain()) }

    @EventHandler
    fun onDeathEvent(e:PlayerDeathEvent) {
        val inv:Array<ItemStack?>
        inv = e.player.inventory.contents
        rclr.getData()
        if(rclr[e.player] <= 1){
            e.isCancelled = true
            rclr[e.player] -= 1
            rclr.save()
            e.player.inventory.clear()
            e.player.teleport(e.player.bedLocation)
            e.player.health = 20.0
            e.player.sendMessage("부활 토큰을 하나 사용하여 부활하였습니다!")
        }else{
            e.isCancelled = false
        }

        plugin.server.getWorld(e.player.world.name)!!.setType(e.player.location,Material.CHEST)
        val chest: Chest = plugin.server.getWorld(e.player.world.name)!!.getBlockAt(e.player.location).state as Chest
        val cinv = chest.blockInventory
        inv.forEach {
            if(it!=null){
                cinv.addItem(it)
                chest.update(true)
            }
        }
    }

    @EventHandler
    fun onAchive(e:PlayerAdvancementDoneEvent){
        e.player.sendMessage(e.advancement.toString())
    }
}
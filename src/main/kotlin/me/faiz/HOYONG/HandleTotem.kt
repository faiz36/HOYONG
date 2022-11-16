package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityResurrectEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*
import kotlin.collections.HashMap

// 열심히 구경중 ㅇㅋ!
@Deprecated("No longer used")
class HandleTotem(val plugin: Plugin) : Listener {

    init { Bukkit.getPluginManager().registerEvents(this, plugin) }
    var cooltime:HashMap<UUID,Long> = HashMap()

    @EventHandler
    fun onInteract(e: PlayerInteractEvent){
        if(e.player.inventory.itemInMainHand == ItemStack(Material.TOTEM_OF_UNDYING,1)){
            if(e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK){
                if(cooltime[e.player.uniqueId]==null){
                    cooltime[e.player.uniqueId]=0
                }
                if(cooltime[e.player.uniqueId] == null || cooltime[e.player.uniqueId]!! + 600 <= (System.currentTimeMillis() / 1000)){
                    e.player.sendMessage("불사의 토템 쿨타임이 0초 남았습니다.")
                }
                else{
                    e.player.sendMessage("불사의 토템 쿨타임이 ${cooltime[e.player.uniqueId]!!+600-System.currentTimeMillis()/1000}초 남았습니다.")
                }
            }
        }
    }

    @EventHandler
    fun onTotem(e: EntityResurrectEvent){
        val pl: Player = Bukkit.getPlayer(e.entity.name) ?: return
        if(e.isCancelled) return
        if(cooltime[pl.uniqueId] == null || cooltime[pl.uniqueId]!! + 600 <= (System.currentTimeMillis() / 1000)){
            cooltime[pl.uniqueId] = System.currentTimeMillis()/1000
            pl.setCooldown(Material.TOTEM_OF_UNDYING,20*60*10)
        }else{
            e.isCancelled = true
        }
    }



}
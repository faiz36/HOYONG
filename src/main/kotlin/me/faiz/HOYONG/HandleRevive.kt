package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class HandleRevive(val plugin:Plugin, private val rclr: ReviveController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    // 이쪽 확인좀 잘 작동 안해 ㅋㅋ
    @EventHandler
    fun onDeathEvent(e:PlayerDeathEvent) {
        val inv:Array<ItemStack?> = e.player.inventory.contents
        rclr.getData()
        if(rclr[e.player] >= 1){
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
        inv.forEach {
            if(it != null){
                e.player.world.dropItemNaturally(e.player.location,it)
            }
        }

    }

    @EventHandler
    fun onAchive(e:PlayerAdvancementDoneEvent){
        var sc = 0
        listOf("story/root","story/mine_stone","story/upgrade_tools","story/smelt_iron","story/obtain_armor","story/lava_bucket","story/iron_tools","story/deflect_arrow","story/form_obsidian","story/mine_diamond","story/enter_the_nether","story/shiny_gear","story/enchant_item","story/cure_zombie_villager","story/follow_ender_eye","story/enter_the_end").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                sc += 1
            }
        }
        if(sc == 16){
            rclr.getData()
            rclr[e.player] += 1
            rclr.save()
            e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
        }
    }
}
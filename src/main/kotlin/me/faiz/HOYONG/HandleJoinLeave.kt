package me.faiz.HOYONG

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin

class HandleJoinLeave(plugin: Plugin):Listener {
    init { Bukkit.getPluginManager().registerEvents(this, plugin) }

    @EventHandler
    fun onJoin(e: PlayerJoinEvent){
        e.joinMessage(Component.text("§e${e.player.name}이(가) SMP에 참여했습니다"))
    }

    @EventHandler
    fun onQuit(e: PlayerQuitEvent){
        e.quitMessage(Component.text("§e${e.player.name}이(가) SMP에 떠났습니다"))
    }

}
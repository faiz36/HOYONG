package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.plugin.Plugin

class BanCommand(val plugin: Plugin): Listener {
    init { Bukkit.getPluginManager().registerEvents(this, plugin) }
    // 내가봤을떈 kommand는 혁명이야
    //이거 kommand로 바꾸죠 짜피 안처먹음
    // 펄머션 부여해야하는데 안해서 쓸모가없음
    @EventHandler(priority = EventPriority.LOWEST)
    fun onPreprocess(e: PlayerCommandPreprocessEvent){
        if (listOf("pl", "plugins", "stop", "ver", "version", "help", "?", "op", "deop").any { e.message == it }) {
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        //if(e.message == "pl"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "plugins"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "stop"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "ver"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "version"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "help"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "?"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //
        //if(e.message == "op"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
        //if(e.message == "deop"){
        //    e.isCancelled = true
        //    e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        //}
    }
}
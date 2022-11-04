package me.faiz.HOYONG

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class TotemCommand(val plugin: Plugin, val handleTotem: HandleTotem) {

    init {
        plugin.apply {
            kommand {
                register("totem"){
                    requires { sender.isOp }
                    then("reset"){
                        executes {
                            val pl: Player = server.getPlayer(sender.name)!!
                            handleTotem.cooltime[pl.uniqueId] = 0
                            pl.setCooldown(Material.TOTEM_OF_UNDYING,0)
                            sender.sendMessage("불사의 토템 쿨타임이 초기화 되었습니다")
                        }
                        then("arg" to players()){
                            executes {
                                val arg: Collection<Player> by it
                                arg.forEach {
                                    handleTotem.cooltime[it.uniqueId] = 0
                                    it.setCooldown(Material.TOTEM_OF_UNDYING,0)
                                    sender.sendMessage("불사의 토템 쿨타임이 초기화 되었습니다")
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
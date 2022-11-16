package me.faiz.HOYONG

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ReviveCommand(plugin:Plugin,private val rclr:ReviveController) {

    init {
        plugin.apply {
            kommand{
                register("부활"){
                    then("사용"){
                        then("arg" to string()){
                            executes {
                                val pl: String by it
                                val send = Bukkit.getPlayer(sender.name)!!
                                rclr.getData()
                                if(rclr[send] >= 1){
                                    if(Bukkit.getOfflinePlayer(pl).isBanned){
                                        Bukkit.getServer().bannedPlayers.remove(Bukkit.getOfflinePlayer(pl))
                                        rclr[send] = rclr[send]-1
                                        rclr.save()
                                        sender.sendMessage("${it}님을 부활시켰습니다!")
                                    }else{
                                        sender.sendMessage("${it}님은 죽지않았거나 존재하지않습니다")
                                    }
                                }else{
                                    sender.sendMessage("부활권이 부족합니다!")
                                }
                            }
                        }
                    }
                    then("확인"){
                        executes {
                            rclr.getData()
                            val send = Bukkit.getPlayer(sender.name)!!
                            sender.sendMessage("현재 부활권이 ${rclr[send]}개 남았습니다!")
                        }
                    }
                }
                register("revive"){
                    requires { sender.isOp }
                    then("set"){
                        then("arg" to player()){
                            then("amount" to int()){
                                executes {
                                    val arg: Player by it
                                    val amount: Int by it
                                    rclr[arg] = amount
                                    rclr.save()
                                    sender.sendMessage("${arg.name} revive setted to $amount")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
package me.faiz.HOYONG

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.BanList
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ReviveCommand(plugin:Plugin,private val rclr:ReviveController) {

    init {
        plugin.apply {
            kommand{
                register("부활"){
                    then("사용"){
                        then("pl" to string().apply{
                            suggests {
                                Bukkit.getServer().bannedPlayers.forEach{
                                    suggest(text = "${it.name}")
                                }
                            }
                        }){
                            executes {
                                val pl: String by it
                                val send = Bukkit.getPlayer(sender.name)!!
                                val target = Bukkit.getOfflinePlayer(pl)
                                rclr.getData()
                                if(rclr[send] >= 1){
                                    if(target.isBanned && rclr.getDeath(target.uniqueId)){
                                        Bukkit.getServer().getBanList(BanList.Type.NAME).pardon(target.name!!)
                                        rclr.setDeath(target.uniqueId,false)
                                        rclr.save()
                                        rclr[send] = rclr[send]-1
                                        rclr.save()
                                        sender.sendMessage("${pl}님을 부활시켰습니다!")
                                    }else{
                                        sender.sendMessage("${pl}님은 죽지않았거나 존재하지않습니다")
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
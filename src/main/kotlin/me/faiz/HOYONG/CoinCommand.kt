package me.faiz.HOYONG

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class CoinCommand(val plugin: Plugin, val coin: CoinController,val hc:HandleCoin) {

    init {
        plugin.apply {
            kommand {
                register("후원상점"){
                    executes{
                        val pl: Player? = server.getPlayer(sender.name)
                        hc.onCoin(pl)
                    }
                }
                register("coin"){
                    then("add"){
                        requires { sender.isOp }
                        then("arg" to player()){
                            then("amount" to int()){
                                executes{
                                    val arg: Player by it
                                    val amount: Int by it
                                    val pl:Player = arg
                                    coin.getData()
                                    coin[pl.uniqueId] =+ amount
                                    coin.save()
                                    sender.sendMessage("${pl.name}에게 ${amount}만큼의 코인을 지급했습니다!")
                                }
                            }
                        }
                    }
                    then("remove"){
                        requires { sender.isOp }
                        then("arg" to player()){
                            then("amount" to int()){
                                executes{
                                    val arg: Player by it
                                    val amount: Int by it
                                    val pl:Player = arg
                                    coin.getData()
                                    coin[pl.uniqueId] =- amount
                                    coin.save()
                                    sender.sendMessage("${pl.name}에게 ${amount}만큼의 코인을 지급했습니다!")
                                }
                            }
                        }
                    }
                    then("set"){
                        requires { sender.isOp }
                        then("arg" to player()){
                            then("amount" to int()){
                                executes{
                                    val arg: Player by it
                                    val amount: Int by it
                                    val pl:Player = arg
                                    coin.getData()
                                    coin[pl.uniqueId] = amount
                                    coin.save()
                                    sender.sendMessage("${pl.name}에게 ${amount}만큼의 코인을 지급했습니다!")
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
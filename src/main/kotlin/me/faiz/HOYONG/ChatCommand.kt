package me.faiz.HOYONG

import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class ChatCommand(val plugin: Plugin, val cclr: ChatController) {
    init {
        plugin.apply {
            kommand {
                register("chat") {
                    requires { sender.isOp }
                    then("nickname") {
                        then("player" to player()) {
                            then("name" to string()) {
                                executes {
                                    val name: String by it
                                    val pl: Player by it
                                    name.replace("&o", "")
                                    name.replace("&n", "")
                                    name.replace("&m", "")
                                    name.replace("&k", "")
                                    name.replace("&", "§")
                                    name.replace("§o", "")
                                    name.replace("§n", "")
                                    name.replace("§m", "")
                                    name.replace("§k", "")
                                    cclr.getData()
                                    cclr.setNick(pl, name)
                                    cclr.save()
                                }
                            }
                        }
                    }
                    then("color") {
                        then("player" to player()) {
                            then("color" to string()) {
                                executes {
                                    val color: String by it
                                    val pl: Player by it
                                    color.replace("&o", "")
                                    color.replace("&n", "")
                                    color.replace("&m", "")
                                    color.replace("&k", "")
                                    color.replace("&", "§")
                                    color.replace("§o", "")
                                    color.replace("§n", "")
                                    color.replace("§m", "")
                                    color.replace("§k", "")
                                    cclr.getData()
                                    cclr.setColor(pl, color)
                                    cclr.save()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
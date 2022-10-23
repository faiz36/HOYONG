package me.faiz.HOYONG

import io.github.monun.kommand.StringType
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
                        then("pl" to player()) {
                            then("name" to string(StringType.QUOTABLE_PHRASE)) {
                                executes {
                                    val name: String by it
                                    val pl: Player by it
                                    var rname = name.replace("&", "§")
                                    rname = rname.replace("§o", "")
                                    rname = rname.replace("§n", "")
                                    rname = rname.replace("§m", "")
                                    rname = rname.replace("§k", "")
                                    cclr.getData()
                                    cclr.setNick(pl, rname)
                                    cclr.save()
                                    sender.sendMessage("${pl.name}에게 ${rname}§r이라는 이름을 부여했습니다!")
                                }
                            }
                        }
                    }
                    then("color") {
                        then("pl" to player()) {
                            then("color" to string(StringType.QUOTABLE_PHRASE)) {
                                executes {
                                    val color: String by it
                                    val pl: Player by it
                                    var rcolor = color.replace("&", "§")
                                    rcolor = rcolor.replace("§o", "")
                                    rcolor = rcolor.replace("§n", "")
                                    rcolor = rcolor.replace("§m", "")
                                    rcolor = rcolor.replace("§k", "")
                                    cclr.getData()
                                    cclr.setColor(pl, rcolor)
                                    cclr.save()
                                    sender.sendMessage("${pl.name}에게 ${rcolor}이색§r을 부여했습니다!")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
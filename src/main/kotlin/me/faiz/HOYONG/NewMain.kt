package me.faiz.HOYONG

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

@Suppress("unused")
class NewMain : JavaPlugin() {



    val coinController by lazy { CoinController(this) }
    val ChatController by lazy { ChatController(this) }
    val handleCoin by lazy { HandleCoin(this) }
    val reviveController by lazy { ReviveController(this) }

    override fun onEnable() {

        val dataf = File(dataFolder.toString() + File.separator + "data.yml")
        lateinit var data :FileConfiguration
        if(!dataf.exists()){
            data = YamlConfiguration.loadConfiguration(dataf)
            data.save(dataf)
        }
        HandleChat(this,ChatController)
        HandleEnd(this)
        BanCommand(this)
        HandleCoin(this)
        CoinCommand(this, coinController,handleCoin)
        HandleJoinLeave(this)
        ChatCommand(this,ChatController)
        HandleRevive(this, reviveController)
        ReviveCommand(this, reviveController)
        kommand{
            register("디스코드"){
                executes {
                    sender.sendMessage(
                        Component.text("§6§l[여기]").hoverEvent(HoverEvent.showText(Component.text("누르시면 디스코드 초대창으로 넘어갑니다."))).clickEvent(
                        ClickEvent.openUrl("https://discord.gg/5gJrDDbcxp")).append(Component.text("§f를 눌러 디스코드에 들어오세요!")))
                }
            }
            register("discord"){
                executes {
                    sender.sendMessage(Component.text("§6§l[여기]").hoverEvent(HoverEvent.showText(Component.text("누르시면 디스코드 초대창으로 넘어갑니다."))).clickEvent(
                        ClickEvent.openUrl("https://discord.gg/5gJrDDbcxp")).append(Component.text("§f를 눌러 디스코드에 들어오세요!")))
                }
            }
        }
    }
}
package me.faiz.HOYONG

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

@Suppress("unused")
class NewMain : JavaPlugin() {

    val coinController by lazy { CoinController(this) }
    val handleTotem by lazy { HandleTotem(this) }

    val handleCoin by lazy { HandleCoin(this) }

    override fun onEnable() {
        BanCommand(this)
        HandleCoin(this)
        CoinCommand(this, coinController)
        HandleJoinLeave(this)
        TotemCommand(this, handleTotem)
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
        object : BukkitRunnable() {
            override fun run() {
                val pls = server.onlinePlayers
                pls.forEach {
                    if(it.hasPermission("hoyong.yt")){
                        it.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION,1,999999999))
                        it.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,1,999999999))
                    }
                }
            }

        }.runTaskTimer(this,1L,1L*20*1000)

    }
}
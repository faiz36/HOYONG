package me.faiz.HOYONG

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

        }.runTaskTimer(this,1L,1L)

    }
}
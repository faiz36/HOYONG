package me.faiz.HOYONG

import io.github.monun.kommand.kommand
import net.kyori.adventure.key.Key
import net.kyori.adventure.sound.Sound
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.enchantment.EnchantItemEvent
import org.bukkit.event.inventory.*
import org.bukkit.event.player.PlayerAttemptPickupItemEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


@Suppress("unused")
class Main:JavaPlugin(),Listener {

    override fun onEnable() {
        server.pluginManager.registerEvents(this,this)
        kommand {
            register("디스코드"){
                executes {
                    sender.sendMessage(Component.text("§6§l[여기]").hoverEvent(HoverEvent.showText(Component.text("누르시면 디스코드 초대창으로 넘어갑니다."))).clickEvent(
                        ClickEvent.openUrl("discord.gg/5gJrDDbcxp")).append(Component.text("§f를 눌러 디스코드에 들어오세요!")))
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

    @EventHandler
    fun onJoin(e:PlayerJoinEvent){
        e.joinMessage(Component.text("§e${e.player.name}이(가) SMP에 참여했습니다"))
    }

    @EventHandler
    fun onQuit(e:PlayerQuitEvent){
        e.quitMessage(Component.text("§e${e.player.name}이(가) SMP에 떠났습니다"))
    }

    @EventHandler
    fun onPickup(e:PlayerAttemptPickupItemEvent){
        val pl = e.player
        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
        val ci = e.item.itemStack
        if(ci == ItemStack(Material.TOTEM_OF_UNDYING)||
            ci == ItemStack(Material.DIAMOND_SWORD)||
            ci == ItemStack(Material.DIAMOND_AXE)||
            ci == ItemStack(Material.NETHERITE_SWORD)||
            ci == ItemStack(Material.NETHERITE_AXE)||
            ci == ItemStack(Material.NETHERITE_HELMET)||
            ci == ItemStack(Material.NETHERITE_CHESTPLATE)||
            ci == ItemStack(Material.NETHERITE_LEGGINGS)||
            ci == ItemStack(Material.NETHERITE_BOOTS)) {
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onInv(e:InventoryClickEvent){
        val inv = e.clickedInventory?.type
        if(inv == InventoryType.PLAYER||
            inv == InventoryType.SMITHING||
            inv == InventoryType.CRAFTING) return
        val pl = e.whoClicked
        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
        val ci = e.currentItem
        if(inv == InventoryType.ANVIL){
            if(ci != null){
            if(ci == ItemStack(Material.BOW) &&
                ci.enchantments.containsKey(Enchantment.ARROW_DAMAGE) ||
                ci.enchantments.containsValue(4)||
                ci.enchantments.containsValue(5)) {
                e.isCancelled = true
                pl.sendMessage("§c이 인챈트를 하실 수 없습니다")
                pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
            }
            }
        }
        if(ci == ItemStack(Material.TOTEM_OF_UNDYING)||
            ci == ItemStack(Material.DIAMOND_SWORD)||
            ci == ItemStack(Material.DIAMOND_AXE)||
            ci == ItemStack(Material.NETHERITE_SWORD)||
            ci == ItemStack(Material.NETHERITE_AXE)||
            ci == ItemStack(Material.NETHERITE_HELMET)||
            ci == ItemStack(Material.NETHERITE_CHESTPLATE)||
            ci == ItemStack(Material.NETHERITE_LEGGINGS)||
            ci == ItemStack(Material.NETHERITE_BOOTS)){
            e.isCancelled = true
            pl.sendMessage("§c이 아이템은 이동하실 수 없습니다")
            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
        }
    }

    @EventHandler
    fun onEnchant(e:EnchantItemEvent){
        val pl = e.enchanter
        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
        if(e.enchantsToAdd.containsKey(Enchantment.ARROW_DAMAGE) &&
            e.enchantsToAdd.containsValue(4)||
            e.enchantsToAdd.containsValue(5)){
            e.isCancelled = true
            pl.sendMessage("§c이 인첸트를 하실 수 없습니다(힘4&5 인첸트가 아닌데 뜬다면 추가 인첸트로 떠서 그렇습니다)")
            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
        }
    }

    @EventHandler
    fun onSmith(e:SmithItemEvent){
        val pl = e.whoClicked
        val ci = e.currentItem
        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
        if(ci == ItemStack(Material.NETHERITE_SWORD)||
            ci == ItemStack(Material.NETHERITE_AXE)||
            ci == ItemStack(Material.NETHERITE_HELMET)||
            ci == ItemStack(Material.NETHERITE_CHESTPLATE)||
            ci == ItemStack(Material.NETHERITE_LEGGINGS)||
            ci == ItemStack(Material.NETHERITE_BOOTS)){
            e.isCancelled = true
            pl.sendMessage("§c이 아이템은 제작하실 수 없습니다")
            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
        }
    }

    @EventHandler
    fun onCraft(e:CraftItemEvent){
        val pl = e.whoClicked
        val ci = e.currentItem
        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
        if(ci == ItemStack(Material.DIAMOND_SWORD)||
            ci == ItemStack(Material.DIAMOND_AXE)){
            e.isCancelled = true
            pl.sendMessage("§c이 아이템은 제작하실 수 없습니다")
            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
        }
    }


    @EventHandler
    fun onPreprocess(e: PlayerCommandPreprocessEvent){
        if(e.message == "pl"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "plugins"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "stop"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "ver"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "version"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "help"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "?"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }

        else if(e.message == "op"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        else if(e.message == "deop"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
    }
}
package me.faiz.HOYONG

import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntityResurrectEvent
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable


@Suppress("unused")
class Main:JavaPlugin(),Listener {

    var atk = false
    var cooltime:HashMap<Player,Long> = HashMap()

    override fun onEnable() {
        server.pluginManager.registerEvents(this,this)
        object : BukkitRunnable() {
            override fun run() {
                val pls = server.onlinePlayers
                pls.forEach {
                    if(it.hasPermission("hoyong.yt")){
                        it.addPotionEffect(PotionEffect(PotionEffectType.REGENERATION,2,0))
                        it.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,2,0))
                    }
                }
            }

        }.runTaskTimer(this@Main,1L,1L)
        kommand {
            register("디스코드"){
                executes {
                    sender.sendMessage(Component.text("§6§l[여기]").hoverEvent(HoverEvent.showText(Component.text("누르시면 디스코드 초대창으로 넘어갑니다."))).clickEvent(
                        ClickEvent.openUrl("https://discord.gg/5gJrDDbcxp")).append(Component.text("§f를 눌러 디스코드에 들어오세요!")))
                }
            }
            register("discord"){
                executes {
                    sender.sendMessage(Component.text("§6§l[여기]").hoverEvent(HoverEvent.showText(Component.text("누르시면 디스코드 초대창으로 넘어갑니다."))).clickEvent(
                        ClickEvent.openUrl("https://discord.gg/5gJrDDbcxp")).append(Component.text("§f를 눌러 디스코드에 들어오세요!")))
                }
            }
            register("smp"){
                requires { sender.isOp }
                then("start"){
                    executes {
                        server.getWorld("world")!!.worldBorder.size = 10000.0
                        atk = false
                        var time = 600
                        object : BukkitRunnable() {
                            override fun run() {
                                if(time > 0){
                                    val pls = server.onlinePlayers
                                    time--
                                    pls.forEach {
                                        it.sendActionBar(Component.text("PVP 가능 까지 ${time}초 남았습니다"))
                                    }
                                }else if(time==0){
                                    atk=true
                                    server.broadcast(Component.text("§l이제 PVP가 가능합니다!"))
                                    cancel()
                                }
                            }
                        }.runTaskTimer(this@Main,20L*1L,20L*1L)
                    }
                }
            }
        }
    }

    @EventHandler
    fun onInteract(e:PlayerInteractEvent){
        if(e.player.inventory.itemInMainHand== ItemStack(Material.TOTEM_OF_UNDYING,1)){
            if(e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK){
                if(cooltime[e.player]==null){
                    cooltime[e.player]=0
                }
                if(cooltime[e.player] == null || cooltime[e.player]!! + 600 <= (System.currentTimeMillis() / 1000)){
                    e.player.sendMessage("불사의 토템 쿨타임이 0초 남았습니다.")
                    }
                else{
                e.player.sendMessage("불사의 토템 쿨타임이 ${cooltime[e.player]!!+600-System.currentTimeMillis()/1000}초 남았습니다.")
                }
            }
        }
    }

    @EventHandler
    fun onDie(e:EntityDeathEvent){
        if(e.entityType==EntityType.ENDER_DRAGON){
            server.getWorld("world_the_end")!!.getBlockAt(0,65,0).type = Material.DRAGON_EGG
        }
    }

    @EventHandler
    fun onTotem(e: EntityResurrectEvent){
        val pl: Player = server.getPlayer(e.entity.name) ?: return
        if(e.isCancelled) return
        if(cooltime[pl] == null || cooltime[pl]!! + 600 <= (System.currentTimeMillis() / 1000)){
            cooltime[pl] = System.currentTimeMillis()/1000
            pl.setCooldown(Material.TOTEM_OF_UNDYING,20*60*10)
        }else{
            e.isCancelled = true
        }
    }

    @EventHandler
    fun onHit(e: EntityDamageByEntityEvent){
        if(e.entityType!=EntityType.PLAYER) return
        if(e.damager.type == EntityType.PLAYER&&atk) e.isCancelled=true
    }

    @EventHandler
    fun onPreprocess(e: PlayerCommandPreprocessEvent){
        if(e.message == "pl"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "plugins"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "stop"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "ver"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "version"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "help"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "?"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }

        if(e.message == "op"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
        }
        if(e.message == "deop"){
            e.isCancelled = true
            e.player.sendMessage("§c이 명령어는 사용할 수 없습니다")
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

//    @EventHandler
//    fun onPickup(e:PlayerAttemptPickupItemEvent){
//        val pl = e.player
//        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
//        val ci = e.item.itemStack
//        if(ci.type == Material.TOTEM_OF_UNDYING||
//            ci.type == Material.DIAMOND_SWORD||
//            ci.type == Material.NETHERITE_SWORD||
//            ci.type == Material.NETHERITE_AXE||
//            ci.type == Material.NETHERITE_HELMET||
//            ci.type == Material.NETHERITE_CHESTPLATE||
//            ci.type == Material.NETHERITE_LEGGINGS||
//            ci.type == Material.NETHERITE_BOOTS) {
//            e.isCancelled = true
//        }
//    }

//    @EventHandler
//    fun onInv(e:InventoryClickEvent){
//        val inv = e.clickedInventory?.type
//        if(inv == InventoryType.PLAYER||
//            inv == InventoryType.SMITHING||
//            inv == InventoryType.CRAFTING) return
//        val pl = e.whoClicked
//        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
//        val ci = e.currentItem
//        if(inv == InventoryType.ANVIL){
//            if(ci != null){
//                if(ci.type == Material.DIAMOND_SWORD &&
//                        ci.enchantments.containsKey(Enchantment.DAMAGE_ALL)){
//                    e.isCancelled = true
//                    pl.sendMessage("§c이 인챈트를 하실 수 없습니다")
//                    pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//                }
//            if(ci.type == Material.BOW &&
//                ci.enchantments.containsKey(Enchantment.ARROW_DAMAGE) ||
//                ci.enchantments.containsValue(4)||
//                ci.enchantments.containsValue(5)) {
//                e.isCancelled = true
//                pl.sendMessage("§c이 인챈트를 하실 수 없습니다")
//                pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//            }
//            }
//        }
//        if(ci?.type == Material.TOTEM_OF_UNDYING||
//            ci?.type == Material.DIAMOND_SWORD||
//            ci?.type == Material.NETHERITE_SWORD||
//            ci?.type == Material.NETHERITE_AXE||
//            ci?.type == Material.NETHERITE_HELMET||
//            ci?.type == Material.NETHERITE_CHESTPLATE||
//            ci?.type == Material.NETHERITE_LEGGINGS||
//            ci?.type == Material.NETHERITE_BOOTS){
//            e.isCancelled = true
//            pl.sendMessage("§c이 아이템은 이동하실 수 없습니다")
//            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//        }
//    }
//
//    @EventHandler
//    fun onEnchant(e:EnchantItemEvent){
//        val pl = e.enchanter
//        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
//        if(e.enchantsToAdd.containsKey(Enchantment.ARROW_DAMAGE) &&
//            e.enchantsToAdd.containsValue(4)||
//            e.enchantsToAdd.containsValue(5)){
//            e.isCancelled = true
//            pl.sendMessage("§c이 인첸트를 하실 수 없습니다(힘4&5 인첸트가 아닌데 뜬다면 추가 인첸트로 떠서 그렇습니다)")
//            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//        }
//        if(e.item.type == Material.DIAMOND_AXE && e.enchantsToAdd.containsKey(Enchantment.DAMAGE_ALL)){
//            e.isCancelled = true
//            pl.sendMessage("§c이 인첸트를 하실 수 없습니다(날카로움 인첸트가 아닌데 뜬다면 추가 인첸트로 떠서 그렇습니다)")
//            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//        }
//    }
//
//    @EventHandler
//    fun onSmith(e:SmithItemEvent){
//        val pl = e.whoClicked
//        val ci = e.currentItem
//        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
//        if(ci?.type == Material.NETHERITE_SWORD||
//            ci?.type == Material.NETHERITE_AXE||
//            ci?.type == Material.NETHERITE_HELMET||
//            ci?.type == Material.NETHERITE_CHESTPLATE||
//            ci?.type == Material.NETHERITE_LEGGINGS||
//            ci?.type == Material.NETHERITE_BOOTS){
//            e.isCancelled = true
//            pl.sendMessage("§c이 아이템은 제작하실 수 없습니다")
//            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//        }
//    }
//
//    @EventHandler
//    fun onCraft(e:CraftItemEvent){
//        val pl = e.whoClicked
//        val ci = e.currentItem
//        if(pl.hasPermission("hoyong.yt")||pl.isOp) return
//        if(ci?.type == Material.DIAMOND_SWORD){
//            e.isCancelled = true
//            pl.sendMessage("§c이 아이템은 제작하실 수 없습니다")
//            pl.playSound(Sound.sound(Key.key("block.anvil.place"),Sound.Source.BLOCK,1f,1f))
//        }
//    }

}
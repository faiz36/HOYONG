package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.EntityEffect
import org.bukkit.NamespacedKey
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerAdvancementDoneEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin

class HandleRevive(val plugin:Plugin, private val rclr: ReviveController):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    @EventHandler
    fun onDeathEvent(e:PlayerDeathEvent) {
        val inv:Array<ItemStack?> = e.player.inventory.contents
        rclr.getData()
        if(rclr[e.player] >= 1){
            e.isCancelled = true
            rclr[e.player] -= 1
            rclr.save()
            e.player.inventory.clear()
            if(e.player.bedSpawnLocation != null){
                e.player.teleportAsync(e.player.bedSpawnLocation!!)
            }else{
                e.player.teleportAsync(e.player.world.spawnLocation)
            }
            e.player.playEffect(EntityEffect.TOTEM_RESURRECT)
            e.player.fireTicks = 0
            e.player.saturation = 7f
            e.player.foodLevel = 20
            e.player.health = 20.0
            e.player.sendMessage("부활 토큰을 하나 사용하여 부활하였습니다!")
        }else{
            e.player.banPlayer("사망하셨습니다")
            rclr.setDeath(e.player,true)
            rclr.save()
        }
        inv.forEach {
            if(it != null){
                e.player.world.dropItemNaturally(e.player.location,it)
            }
        }

    }

    @EventHandler
    fun onAchive(e:PlayerAdvancementDoneEvent){
        rclr.getData()
        var sc = 0
        var nc = 0
        var ec = 0
        var ac = 0
        var hc = 0
        listOf("story/root","story/mine_stone","story/upgrade_tools","story/smelt_iron","story/obtain_armor","story/lava_bucket","story/iron_tools","story/deflect_arrow","story/form_obsidian","story/mine_diamond","story/enter_the_nether","story/shiny_gear","story/enchant_item","story/cure_zombie_villager","story/follow_ender_eye","story/enter_the_end").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                sc += 1
            }
        }
        if(sc == 16){
            if(!rclr.getSt(e.player)){
                rclr.getData()
                rclr[e.player] += 1
                rclr.setSt(e.player,true)
                rclr.save()
                e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
            }
        }
        listOf("nether/root","nether/return_to_sender","nether/find_bastion","nether/obtain_ancient_debris","nether/fast_travel","nether/find_fortress","nether/obtain_crying_obsidian","nether/distract_piglin","nether/ride_strider","nether/uneasy_alliance","nether/loot_bastion","nether/use_lodestone","nether/netherite_armor","nether/get_wither_skull","nether/obtain_blaze_rod","nether/charge_respawn_anchor","nether/ride_strider_in_overworld_lava","nether/explore_nether","nether/summon_wither","nether/brew_potion","nether/create_beacon","nether/all_potions","nether/create_full_beacon","nether/all_effects").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                nc += 1
            }
        }
        if(nc == 24){
            if(!rclr.getNe(e.player)){
                rclr.getData()
                rclr[e.player] += 1
                rclr.setNe(e.player,true)
                rclr.save()
                e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
            }
        }
        listOf("end/root","end/kill_dragon","end/dragon_egg","end/enter_end_gateway","end/respawn_dragon","end/dragon_breath","end/find_end_city","end/elytra","end/levitate").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                ec += 1
            }
        }
        if(ec == 9){
            if(!rclr.getend(e.player)){
                rclr.getData()
                rclr[e.player] += 1
                rclr.setend(e.player,true)
                rclr.save()
                e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
            }
        }
        listOf("adventure/root","adventure/voluntary_exile","adventure/spyglass_at_parrot","adventure/kill_a_mob","adventure/trade","adventure/honey_block_slide","adventure/ol_betsy","adventure/lightning_rod_with_villager_no_fire","adventure/fall_from_world_height","adventure/avoid_vibration","adventure/sleep_in_bed","adventure/hero_of_the_village","adventure/spyglass_at_ghast","adventure/throw_trident","adventure/kill_mob_near_sculk_catalyst","adventure/shoot_arrow","adventure/kill_all_mobs","adventure/totem_of_undying","adventure/summon_iron_golem","adventure/trade_at_world_height","adventure/two_birds_one_arrow","adventure/whos_the_pillager_now","adventure/arbalistic","adventure/adventuring_time","adventure/play_jukebox_in_meadows","adventure/walk_on_powder_snow_with_leather_boots","adventure/spyglass_at_dragon","adventure/very_very_frightening","adventure/sniper_duel","adventure/bullseye").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                ac += 1
            }
        }
        if(ac == 30){
            if(!rclr.getAdv(e.player)){
                rclr.getData()
                rclr[e.player] += 1
                rclr.setAdv(e.player,true)
                rclr.save()
                e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
            }
        }
        listOf("husbandry/root","husbandry/safely_harvest_honey","husbandry/breed_an_animal","husbandry/allay_deliver_item_to_player","husbandry/ride_a_boat_with_a_goat","husbandry/tame_an_animal","husbandry/make_a_sign_glow","husbandry/fishy_business","husbandry/silk_touch_nest","husbandry/tadpole_in_a_bucket","husbandry/plant_seed","husbandry/wax_on","husbandry/bred_all_animals","husbandry/allay_deliver_cake_to_note_block","husbandry/complete_catalogue","husbandry/tactical_fishing","husbandry/leash_all_frog_variants","husbandry/balanced_diet","husbandry/obtain_netherite_hoe","husbandry/wax_off","husbandry/axolotl_in_a_bucket","husbandry/froglights","husbandry/kill_axolotl_target").forEach {
            if(!e.player.getAdvancementProgress(Bukkit.getAdvancement(NamespacedKey.minecraft(it))!!).isDone){
                return
            }else{
                hc += 1
            }
        }
        if(hc == 23){
            if(!rclr.gethusbandry(e.player)){
                rclr.getData()
                rclr[e.player] += 1
                rclr.sethusbandry(e.player,true)
                rclr.save()
                e.player.sendMessage("도전과제 한 페이지를 달성하여 부활권이 지급되었습니다!")
            }
        }
    }
}
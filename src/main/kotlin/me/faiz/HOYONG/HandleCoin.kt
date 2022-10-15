package me.faiz.HOYONG

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.plugin.Plugin

lateinit var inv: Inventory
lateinit var knamet: ItemStack
class HandleCoin(val plugin:Plugin):Listener {

    init { Bukkit.getPluginManager().registerEvents(this,plugin) }

    @EventHandler
    fun onInv(e: InventoryClickEvent){
        if(e.inventory.equals(inv)){
            if(e.currentItem == knamet){
                e.whoClicked.sendMessage("테스트")
            }
        }
    }



}

fun onCoin(pl: Player?){
    inv = Bukkit.createInventory(null,9, Component.text("§l§b후원상점"))
    knamet = ItemStack(Material.NAME_TAG)
    var knametm:ItemMeta = knamet.itemMeta
    knametm.displayName(Component.text("한글 닉네임"))
    knamet.itemMeta = knametm
    inv.setItem(1,knamet)
    pl!!.openInventory(inv)
}
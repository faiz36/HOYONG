package me.faiz.HOYONG

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.EntityResurrectEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import java.util.*
import kotlin.collections.HashMap

// 열심히 구경중 ㅇㅋ!
class HandleTotem(val plugin: Plugin) : Listener {

    // 이 ㅅ1끼의 역할은 뭔가요 저는 개뉴비라 모릅니다 화긴
    //생성자라는 것을 아십니까???????
    //클래스라는 것은 객체로써 무언가를 정의하기에 조오오흔 개념이죠
    //그렇기 때문에 클래스라는 개념이 있다면 그것은 객체지향 언어에요
    //그리고 아주 간단하고 심플한 개념이지만, 너무 여럽다고 착각을 해요
    //그냥 어떠한 설계와 그 설계도를 바탕으로 메모리에 생성된 복제본 느낌
    //설계도 : 객체 또는 클래스 object or class
    //복제본 : 인스턴스 instance
    //그러니까 우리가 작성하는 코드는 프로그램의 설계도에요
    //그리고 그 설계도가 실제로 메모리에 존재하는 것은 인스턴스에요
    //그리고 설계도를 즉 객체에 이름을 정해주고
    //그 이름으로 막 메모리에 내가 설계한 코드를 막 생성하는거에요
    //그런데 그래서 생성자라는 것은
    //객체(설게도)가 메모리에 로드되는 시점에 실행되는 아주 특별한 함수죠.
    //이건 생성잡니다 init
    init { Bukkit.getPluginManager().registerEvents(this, plugin) }
    var cooltime:HashMap<UUID,Long> = HashMap() // 불토 쿨타임

    @EventHandler
    fun onInteract(e: PlayerInteractEvent){
        if(e.player.inventory.itemInMainHand == ItemStack(Material.TOTEM_OF_UNDYING,1)){
            if(e.action == Action.RIGHT_CLICK_AIR || e.action == Action.RIGHT_CLICK_BLOCK){
                if(cooltime[e.player.uniqueId]==null){
                    cooltime[e.player.uniqueId]=0
                }
                if(cooltime[e.player.uniqueId] == null || cooltime[e.player.uniqueId]!! + 600 <= (System.currentTimeMillis() / 1000)){
                    e.player.sendMessage("불사의 토템 쿨타임이 0초 남았습니다.")
                }
                else{
                    e.player.sendMessage("불사의 토템 쿨타임이 ${cooltime[e.player.uniqueId]!!+600-System.currentTimeMillis()/1000}초 남았습니다.")
                }
            }
        }
    }

    @EventHandler
    fun onTotem(e: EntityResurrectEvent){
        val pl: Player = Bukkit.getPlayer(e.entity.name) ?: return
        if(e.isCancelled) return
        if(cooltime[pl.uniqueId] == null || cooltime[pl.uniqueId]!! + 600 <= (System.currentTimeMillis() / 1000)){
            cooltime[pl.uniqueId] = System.currentTimeMillis()/1000
            pl.setCooldown(Material.TOTEM_OF_UNDYING,20*60*10)
        }else{
            e.isCancelled = true
        }
    }



}
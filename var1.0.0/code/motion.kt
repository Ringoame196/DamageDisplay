package com.github.Ringoame196

import org.bukkit.entity.ArmorStand
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class motion() {
    fun summon(location: org.bukkit.Location, message: String, plugin: Plugin) {
        val world = location.world
        // アーマースタンドを召喚
        location.add(0.0, 0.0, 0.0)
        val armorStand: ArmorStand? = world?.spawn(location, ArmorStand::class.java)
        armorStand?.let {
            // アーマースタンドの設定
            it.isVisible = false // 可視化するかどうか
            it.isSmall = true // サイズを小さくするかどうか
            it.isInvulnerable = true
            it.customName = message
            it.isCustomNameVisible = true
            it.setGravity(false)

            object : BukkitRunnable() {
                override fun run() {
                    it.remove()
                }
            }.runTaskLater(plugin, 20) // 20 ticks = 1秒
        }
    }
}

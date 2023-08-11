package com.github.Ringoame196

import org.bukkit.ChatColor
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.plugin.Plugin

class Events(private val plugin: Plugin) : Listener {
    @EventHandler
    fun onMobDamage(e: EntityDamageByEntityEvent) {
        val damagedEntity = e.entity
        val damager = e.damager
        val damage = e.getDamage()
        val HP = if (damagedEntity is LivingEntity) {
            val newHP = (damagedEntity.health - damage).coerceAtLeast(0.0)
            newHP.toInt()
        } else { 0 }
        if (damagedEntity is ArmorStand) { return }
        if (damager is Player || (damager is Projectile && damager.shooter is Player)) { motion().summon(damagedEntity.location, "${ChatColor.RED}${damage.toInt()}ダメージ [${HP}HP]", plugin) }
    }
}

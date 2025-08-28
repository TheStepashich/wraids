package org.thestepashich.wRaids;

import org.bukkit.Location;
import org.bukkit.Raid;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Raider;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.event.raid.RaidTriggerEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class WRaids extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
    }
    @Override
    public void onDisable() {}
}
class EventListener implements Listener {

    private final Map<Raid, Player> raidStarterMap = new HashMap<>();

    @EventHandler
    public void onRaidStart(RaidTriggerEvent event) {
        Raid raid = event.getRaid();
        Player starter = event.getPlayer();
        raidStarterMap.put(raid, starter);
    }

    @EventHandler
    private void onRaidSpawn(RaidSpawnWaveEvent event) {
        Raid raid = event.getRaid();
        Player starter = raidStarterMap.get(raid);
        List<Raider> raiders = event.getRaiders();

        if (raiders.isEmpty()) {
            return;
        }

        Raider firstRaider = raiders.getFirst();
        Location spawnLocation = firstRaider.getLocation();
        Vex vex = (Vex) firstRaider.getWorld().spawnEntity(spawnLocation, EntityType.VEX);
        vex.setCharging(true);
        if (starter != null && starter.isOnline()) {
            vex.setTarget(starter);
        }
    }
}
package org.thestepashich.wRaids;

import org.bukkit.entity.Raider;
import org.bukkit.entity.Vex;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.raid.RaidSpawnWaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class WRaids extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
    }
    @Override
    public void onDisable() {}
}
class EventListener implements Listener {

    @EventHandler
    private void onRaidSpawn(RaidSpawnWaveEvent event){
        List<Raider> raiders = event.getRaiders();
        Raider r = raiders.getFirst();
        r.getWorld().spawn(r.getLocation(), Vex.class);
    }
}
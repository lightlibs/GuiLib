package com.tcoded.lightlibs.guilib;

import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.listener.GuiListener;
import com.tcoded.lightlibs.guilib.manager.LayoutManager;
import com.tcoded.lightlibs.guilib.types.GuiLibHolder;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@SuppressWarnings("unused")
public final class GuiLib <ContextType extends AbstractGuiContext> {

    private final JavaPlugin plugin;

    private final LayoutManager<ContextType> layoutManager;
    private final GuiListener<ContextType> guiListener;

    public GuiLib(JavaPlugin plugin) {
        // Init
        this.plugin = plugin;

        // Managers
        this.layoutManager = new LayoutManager<>();

        // Listeners
        PluginManager pluginManager = plugin.getServer().getPluginManager();
        this.guiListener = new GuiListener<>();
        pluginManager.registerEvents(guiListener, plugin);
    }

    public void onDisable() {
        this.onDisable(player -> {
            player.closeInventory();
            return CompletableFuture.completedFuture(null);
        });
    }

    public void onDisable(Function<Player, CompletableFuture<Void>> playerCloseInv) {
        for (Player player : this.plugin.getServer().getOnlinePlayers()) {
            Inventory inv = player.getOpenInventory().getTopInventory();
            if (!(inv.getHolder() instanceof GuiLibHolder)) continue;
            playerCloseInv.apply(player);
        }

        if (guiListener != null) {
            HandlerList.unregisterAll(guiListener);
        }
    }

    public LayoutManager<ContextType> getLayoutManager() {
        return this.layoutManager;
    }

}

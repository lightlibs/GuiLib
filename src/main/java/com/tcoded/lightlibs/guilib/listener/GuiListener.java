package com.tcoded.lightlibs.guilib.listener;

import com.tcoded.lightlibs.guilib.context.ClickEventContext;
import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.types.GuiInstance;
import com.tcoded.lightlibs.guilib.types.GuiLibHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

public class GuiListener <ContextType extends AbstractGuiContext> implements Listener {

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        InventoryView invView = event.getView();

        Inventory topInv = invView.getTopInventory();
        if (!(topInv.getHolder() instanceof GuiLibHolder)) return;

        // Cancel if any items are in the top inventory slots
        for (Integer slot : event.getRawSlots()) {
            if (slot < topInv.getSize()) {
                event.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView invView = event.getView();

        Inventory topInv = invView.getTopInventory();
        if (!(topInv.getHolder() instanceof GuiLibHolder)) return;
        // noinspection unchecked // guaranteed
        GuiLibHolder<ContextType> holder = (GuiLibHolder<ContextType>) topInv.getHolder();

        GuiInstance<ContextType> gui = holder.getGuiInstance();
        if (gui == null) return;

        ContextType guiCtx = gui.getContext();
        // noinspection ConstantValue // check anyway
        if (guiCtx == null) return;


        boolean isTopInv = topInv.equals(event.getClickedInventory());
        ClickEventContext<ContextType> clickCtx = new ClickEventContext<>(guiCtx, gui, event, isTopInv);

        try {
            gui.runAction(event.getRawSlot(), clickCtx);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!clickCtx.isAllowClick()) event.setCancelled(true);
    }

}

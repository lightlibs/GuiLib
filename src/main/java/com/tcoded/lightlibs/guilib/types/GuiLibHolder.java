package com.tcoded.lightlibs.guilib.types;

import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class GuiLibHolder<ContextType extends AbstractGuiContext> implements InventoryHolder {

    private final ContextType ctx;

    private Inventory inventory;
    private GuiInstance<ContextType> guiInstance;

    public GuiLibHolder(ContextType ctx) {
        this.ctx = ctx;
    }

    @NotNull
    public ContextType getContext() {
        return this.ctx;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public void setGuiInstance(GuiInstance<ContextType> guiInstance) {
        this.guiInstance = guiInstance;
    }

    @Nullable
    public GuiInstance<ContextType> getGuiInstance() {
        return guiInstance;
    }

}

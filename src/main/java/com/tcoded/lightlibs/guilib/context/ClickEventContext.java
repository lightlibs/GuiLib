package com.tcoded.lightlibs.guilib.context;

import com.tcoded.lightlibs.guilib.types.GuiInstance;
import org.bukkit.event.inventory.InventoryClickEvent;

@SuppressWarnings("unused")
public final class ClickEventContext <ContextType extends AbstractGuiContext> {

    private final ContextType guiContext;
    private final GuiInstance<ContextType> guiInstance;
    private final InventoryClickEvent clickEvent;
    private final boolean isTopInv;

    private boolean allowClick;

    public ClickEventContext(ContextType guiContext, GuiInstance<ContextType> guiInstance, InventoryClickEvent clickEvent, boolean isTopInv) {
        this.guiContext = guiContext;
        this.guiInstance = guiInstance;
        this.clickEvent = clickEvent;
        this.isTopInv = isTopInv;

        this.allowClick = false;
    }

    public ContextType getGuiContext() {
        return guiContext;
    }

    public GuiInstance<ContextType> getGuiInstance() {
        return guiInstance;
    }

    public InventoryClickEvent getClickEvent() {
        return clickEvent;
    }

    public boolean isTopInv() {
        return isTopInv;
    }

    public boolean isAllowClick() {
        return allowClick;
    }

    public void setAllowClick(boolean allowClick) {
        this.allowClick = allowClick;
    }

}

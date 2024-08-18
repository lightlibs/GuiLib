package com.tcoded.lightlibs.guilib.types;

import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.context.ClickEventContext;
import com.tcoded.lightlibs.guilib.gui.AbstractGuiLayout;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GuiInstance <ContextType extends AbstractGuiContext> {

    private final Inventory inventory;
    private final AbstractGuiLayout<ContextType> layout;
    private final ContextType context;

    private final List<GuiAction<ContextType>> handlers;
    private final Map<Integer, GuiAction<ContextType>> actions;

    public GuiInstance(Inventory inventory, AbstractGuiLayout<ContextType> layout, ContextType context) {
        this.inventory = inventory;
        this.layout = layout;
        this.context = context;

        this.handlers = new ArrayList<>();
        this.actions = new HashMap<>();
    }

    public void applyLayout() {
        this.layout.applyLayoutItems(this);
        this.layout.applyDefaultActions(this);
        this.layout.applyClickHandlers(this);
    }

    public void runAction(int slot, ClickEventContext<ContextType> ctx) {

        // Trigger general click handlers
        for (GuiAction<ContextType> handler : this.handlers) {
            handler.accept(ctx);
        }

        // Trigger slot-specific click actions
        Consumer<ClickEventContext<ContextType>> action = actions.get(slot);
        if (action != null) action.accept(ctx);
    }

    @NotNull
    public Inventory getInventory() {
        return this.inventory;
    }

    @NotNull
    public ContextType getContext() {
        return this.context;
    }

    @NotNull
    public AbstractGuiLayout<ContextType> getLayout() {
        return this.layout;
    }

    public void setAction(int slot, GuiAction<ContextType> action) {
        this.actions.put(slot, action);
    }

    public void addClickHandler(GuiAction<ContextType> handler) {
        this.handlers.add(handler);
    }

}

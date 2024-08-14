package com.tcoded.lightlibs.guilib.gui;

import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.context.OpenGuiContext;
import com.tcoded.lightlibs.guilib.types.GuiAction;
import com.tcoded.lightlibs.guilib.types.GuiInstance;
import com.tcoded.lightlibs.guilib.types.GuiLibHolder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unused")
public abstract class AbstractGuiLayout <ContextType extends AbstractGuiContext> {

    private final List<GuiAction<ContextType>> clickHandlers = new ArrayList<>();
    private final HashMap<Integer, ItemStack> defaultItems = new HashMap<>();
    private final HashMap<Integer, GuiAction<ContextType>> defaultActions = new HashMap<>();

    protected void addClickHandler(GuiAction<ContextType> action) {
        this.clickHandlers.add(action);
    }

    protected void addIcon(int slot, ItemStack item) {
        this.defaultItems.put(slot, item);
    }

    protected void addAction(int slot, GuiAction<ContextType> action) {
        this.defaultActions.put(slot, action);
    }

    protected void addButton(int slot, ItemStack item, GuiAction<ContextType> action) {
        addIcon(slot, item);
        addAction(slot, action);
    }

    public void applyLayoutItems(GuiInstance<ContextType> gui) {
        Inventory inventory = gui.getInventory();
        ItemStack bg = getBackground();
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = this.defaultItems.get(i);
            if (item == null) item = bg;
            if (item == null) continue;
            inventory.setItem(i, item);
        }
    }

    public void applyDefaultActions(GuiInstance<ContextType> gui) {
        this.defaultActions.forEach(gui::setAction);
    }

    public void applyClickHandlers(GuiInstance<ContextType> gui) {
        System.out.println("applying " + this.clickHandlers.size() + " click handlers");
        this.clickHandlers.forEach(gui::addClickHandler);
    }

    public GuiLibHolder<ContextType> createInventory(OpenGuiContext<ContextType> ctx) {
        GuiLibHolder<ContextType> holder = new GuiLibHolder<>(ctx.getGuiContext());

        Inventory inventory = Bukkit.createInventory(holder, getSize(), getTitle());
        holder.setInventory(inventory);

        return holder;
    }

    protected abstract ItemStack getBackground();

    protected abstract String getTitle();

    protected abstract int getSize();

}

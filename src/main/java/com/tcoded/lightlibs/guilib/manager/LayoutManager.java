package com.tcoded.lightlibs.guilib.manager;

import com.tcoded.lightlibs.guilib.context.OpenGuiContext;
import com.tcoded.lightlibs.guilib.gui.AbstractGuiLayout;
import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.types.GuiInstance;
import com.tcoded.lightlibs.guilib.types.GuiLibHolder;
import org.bukkit.inventory.Inventory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class LayoutManager <ContextType extends AbstractGuiContext> {

    private final Map<Class<? extends AbstractGuiLayout<ContextType>>, AbstractGuiLayout<ContextType>> layouts;

    public LayoutManager() {
        this.layouts = Collections.synchronizedMap(new HashMap<>());
    }

    public void registerLayout(AbstractGuiLayout<ContextType> layout) {
        // noinspection unchecked // guaranteed
        this.layouts.put((Class<? extends AbstractGuiLayout<ContextType>>) layout.getClass(), layout);
    }

    public GuiInstance<ContextType> createInstance(Class<? extends AbstractGuiLayout<ContextType>> clazz, ContextType ctx) {
        AbstractGuiLayout<ContextType> layout = getLayout(clazz);
        if (layout == null) throw new IllegalArgumentException("There is no GUI registered for class: " + clazz.getName());

        OpenGuiContext<ContextType> openGuiContext = new OpenGuiContext<>(ctx);
        GuiLibHolder<ContextType> holder = layout.createInventory(openGuiContext);

        Inventory inventory = holder.getInventory();

        // Gui Instance
        GuiInstance<ContextType> guiInstance = new GuiInstance<>(inventory, layout, ctx);
        holder.setGuiInstance(guiInstance);
        guiInstance.applyLayout();

        return guiInstance;
    }

    public AbstractGuiLayout<ContextType> getLayout(Class<? extends AbstractGuiLayout<ContextType>> clazz) {
        return layouts.get(clazz);
    }

}

package com.tcoded.lightlibs.guilib.context;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public abstract class AbstractGuiContext {

    private final Player player;

    public AbstractGuiContext(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}

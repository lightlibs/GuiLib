package com.tcoded.lightlibs.guilib.context;

@SuppressWarnings("unused")
public class OpenGuiContext<ContextType extends  AbstractGuiContext> {

    private final ContextType guiContext;

    public OpenGuiContext(ContextType guiContext) {
        this.guiContext = guiContext;
    }

    public ContextType getGuiContext() {
        return guiContext;
    }

}

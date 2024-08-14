package com.tcoded.lightlibs.guilib.types;

import com.tcoded.lightlibs.guilib.context.AbstractGuiContext;
import com.tcoded.lightlibs.guilib.context.ClickEventContext;

import java.util.function.Consumer;

public interface GuiAction<ContextType extends AbstractGuiContext> extends Consumer<ClickEventContext<ContextType>> {
}

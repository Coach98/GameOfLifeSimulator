package com.tyjohtech.gol.logic.editor.tool;

import com.tyjohtech.gol.logic.editor.EditorState;
import com.tyjohtech.gol.model.ModelFactory;
import com.tyjohtech.gol.util.ModelProvider;
import com.tyjohtech.gol.util.command.CommandProcessor;
import com.tyjohtech.gol.util.event.EventBus;

public class ToolModelFactory implements ModelFactory {
    @Override
    public void initialize(ModelProvider propertyBus, EventBus eventBus, CommandProcessor commandProcessor) {
        EditorToolRegistry toolRegistry = propertyBus.get(EditorToolRegistry.class);
        EditorState editorState = propertyBus.get(EditorState.class);

        BrushTool brushTool = new BrushTool(editorState, commandProcessor);
        eventBus.listenFor(BrushConfigEvent.class, brushTool::handle);
        toolRegistry.registerTool(brushTool);
        propertyBus.publish(BrushTool.class, brushTool);

        PencilTool pencilTool = new PencilTool(editorState, commandProcessor);
        toolRegistry.registerTool(pencilTool);
        propertyBus.publish(PencilTool.class, pencilTool);

        SelectionTool selectionTool = new SelectionTool(editorState, commandProcessor);
        toolRegistry.registerTool(selectionTool);
        propertyBus.publish(SelectionTool.class, selectionTool);

        editorState.getSelectedTool().set(brushTool.name());
    }
}

package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;

import cz.morosystems.intellij.plugins.stringfunctions.data.TransformationData;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class ReplaceInEditorAction extends AbstractAction {
	private TransformationProcessor processor;

	public ReplaceInEditorAction(TransformationProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TransformationData transformationData = processor.getTransformationData();
		Editor editor = transformationData.getOpenedEditor();
		if (editor != null) {
			String selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && selectedText.length() > 0) {
				int startPosition = editor.getSelectionModel().getSelectionStart();
				int endPosition = editor.getSelectionModel().getSelectionEnd();
				WriteCommandAction.runWriteCommandAction(editor.getProject(), () -> {
					editor.getDocument().replaceString(startPosition, endPosition, transformationData.getConvertedText());
				});
				editor.getSelectionModel().removeSelection();
			}
		}
	}
}

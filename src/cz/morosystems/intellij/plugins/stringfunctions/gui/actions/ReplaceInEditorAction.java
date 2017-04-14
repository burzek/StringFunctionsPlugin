package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;

import cz.morosystems.intellij.plugins.stringfunctions.data.Document;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class ReplaceInEditorAction extends AbstractAction {
	private Document document;

	public ReplaceInEditorAction(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Editor editor = document.getOpenedEditor();
		if (editor != null) {
			String selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && selectedText.length() > 0) {
				int startPosition = editor.getSelectionModel().getSelectionStart();
				editor.getSelectionModel().removeSelection();
				editor.getDocument().insertString(startPosition, "-XXXX-");
			}
		}
	}
}

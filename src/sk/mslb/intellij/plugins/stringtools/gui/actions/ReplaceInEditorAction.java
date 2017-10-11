package sk.mslb.intellij.plugins.stringtools.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;

import sk.mslb.intellij.plugins.stringtools.data.ConversionData;
import sk.mslb.intellij.plugins.stringtools.data.DataProvider;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class ReplaceInEditorAction extends AbstractAction {

	private DataProvider dataProvider;
	private UpdateStatusListener updateStatusListener;

	public ReplaceInEditorAction(DataProvider dataProvider, UpdateStatusListener updateStatusListener) {
		this.dataProvider = dataProvider;
		this.updateStatusListener = updateStatusListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ConversionData transformationData = dataProvider.getConversionData();
		Editor editor = transformationData.getOpenedEditor();
		if (editor != null) {
			String selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && selectedText.length() > 0) {
				int startPosition = editor.getSelectionModel().getSelectionStart();
				int endPosition = editor.getSelectionModel().getSelectionEnd();
				WriteCommandAction.runWriteCommandAction(editor.getProject(), () -> editor.getDocument().replaceString(startPosition, endPosition, transformationData.getConvertedText()));
				updateStatusListener.updateStatus(ResourceKey.REPLACE_DONE_STATUS);
			} else {
				updateStatusListener.updateStatus(ResourceKey.NO_SELECTION_STATUS);
			}
		}
	}
}

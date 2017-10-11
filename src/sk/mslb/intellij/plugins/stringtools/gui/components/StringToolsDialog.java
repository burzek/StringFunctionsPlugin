package sk.mslb.intellij.plugins.stringtools.gui.components;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import org.jetbrains.annotations.Nullable;

import sk.mslb.intellij.plugins.stringtools.StringToolsController;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class StringToolsDialog extends DialogBuilder {
	private Project project;
	private MainPanel mainPanel;

	public StringToolsDialog(@Nullable Project project) {
		super(project);
		this.project = project;
		initialize();
	}

	private Project getProject() {
		return project;
	}

	public Editor getOpenedEditor() {
		if (project != null) {
			return FileEditorManager.getInstance(project).getSelectedTextEditor();
		}
		return null;
	}


	private void initialize() {
		StringToolsController controller = new StringToolsController(this);
		mainPanel = new MainPanel(controller);
		loadSelectionFromEditor();

		setCenterPanel(mainPanel);
		removeAllActions();
		resizable(false);

	}

	private void loadSelectionFromEditor() {
		Editor editor = FileEditorManager.getInstance(getProject()).getSelectedTextEditor();
		if (editor != null) {
			String selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && selectedText.length() > 0) {
				getMainPanel().setInputContent(selectedText);
			}
		}
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}
}

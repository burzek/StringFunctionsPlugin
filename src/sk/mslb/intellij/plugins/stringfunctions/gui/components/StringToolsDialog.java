package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import org.jetbrains.annotations.Nullable;

import sk.mslb.intellij.plugins.stringfunctions.StringToolsController;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class StringToolsDialog extends DialogBuilder {
	private Project project;
	private StringToolsController controller;
	private MainPanel mainPanel;

	public StringToolsDialog(@Nullable Project project) {
		super(project);
		this.project = project;
		initialize();
	}

	public Project getProject() {
		return project;
	}

	public Editor getOpenedEditor() {
		if (project != null) {
			return FileEditorManager.getInstance(project).getSelectedTextEditor();
		}
		return null;
	}


	private void initialize() {
		controller = new StringToolsController(this);
		mainPanel = new MainPanel(controller);
		setCenterPanel(mainPanel);
		removeAllActions();
		resizable(false);

	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}
}

package cz.morosystems.intellij.plugins.stringfunctions.gui;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogBuilder;
import org.jetbrains.annotations.Nullable;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class StringFunctionsDialog extends DialogBuilder {
	private Project project;


	public StringFunctionsDialog(@Nullable Project project) {
		super(project);
		this.project = project;
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


}

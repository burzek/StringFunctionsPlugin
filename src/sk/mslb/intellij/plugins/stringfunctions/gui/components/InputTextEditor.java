package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.ui.EditorTextField;

import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;

/**
 * @author boris.brinza
 */
public class InputTextEditor extends EditorTextField {
	public InputTextEditor(TransformationRequestListener trl) {
		super();
		getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void documentChanged(DocumentEvent event) {
				trl.transformationRequested();
			}
		});
	}

}

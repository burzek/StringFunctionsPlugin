package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import javax.swing.*;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.EditorTextField;

import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;

/**
 * @author boris.brinza
 */
public class InputTextEditor extends EditorTextField {
	private boolean showWarning;

	public InputTextEditor(TransformationRequestListener trl) {
		super();
		initializeGUI();
		getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void documentChanged(DocumentEvent event) {
				trl.transformationRequested();
			}
		});
	}

	private void initializeGUI() {
		setOneLineMode(false);
		setPreferredSize(new Dimension(300, 50));
		setMinimumSize(new Dimension(300, 50));
	}

	public void showWarning(boolean showWarning) {
		this.showWarning = showWarning;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (showWarning) {
			Icon icon = IconLoader.getTransparentIcon(AllIcons.General.Error);
			icon.paintIcon(this, g, getWidth() - icon.getIconWidth() - 5, getHeight() - icon.getIconHeight() - 5);
		}
	}
}

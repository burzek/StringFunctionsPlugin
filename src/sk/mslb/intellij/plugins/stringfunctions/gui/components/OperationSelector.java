package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.intellij.ui.components.JBRadioButton;

import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;

/**
 * @author boris.brinza
 */
public class OperationSelector extends JBRadioButton {

	private Operation operation;
	private TransformationRequestListener requestListener;

	public OperationSelector(Operation operation, TransformationRequestListener requestListener) {
		this.operation = operation;
		this.requestListener = requestListener;
		addActionListener(e -> requestListener.transformationRequested());
	}
}

package sk.mslb.intellij.plugins.stringtools.gui.components;

import com.intellij.ui.components.JBRadioButton;

import sk.mslb.intellij.plugins.stringtools.data.Operation;
import sk.mslb.intellij.plugins.stringtools.gui.actions.ActionsRequestListener;

/**
 * @author boris.brinza
 */
public class OperationSelector extends JBRadioButton {

	private Operation operation;

	public OperationSelector(Operation operation, ActionsRequestListener requestListener) {
		this.operation = operation;
		addActionListener(e -> requestListener.transformationRequested());
	}

	public Operation getOperation() {
		return operation;
	}
}

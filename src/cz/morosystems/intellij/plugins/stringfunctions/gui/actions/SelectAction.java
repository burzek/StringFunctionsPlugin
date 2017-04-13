package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import cz.morosystems.intellij.plugins.stringfunctions.data.Operation;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class SelectAction extends AbstractAction {

	private OperationSelectionListener selectionListener;
	private Operation operation;

	public SelectAction(OperationSelectionListener selectionListener, Operation operation) {
		this.selectionListener = selectionListener;
		this.operation = operation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		selectionListener.operationSelected(operation);
	}
}

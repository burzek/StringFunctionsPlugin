package sk.mslb.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import sk.mslb.intellij.plugins.stringfunctions.gui.components.StringFunctionsDialog;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class CloseAction extends AbstractAction {

	private StringFunctionsDialog dialog;

	public CloseAction(StringFunctionsDialog dialog) {
		this.dialog = dialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dialog.getDialogWrapper().close(0);
	}
}

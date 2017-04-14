package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import cz.morosystems.intellij.plugins.stringfunctions.conversion.Conversion;
import cz.morosystems.intellij.plugins.stringfunctions.conversion.ConversionFactory;
import cz.morosystems.intellij.plugins.stringfunctions.data.Document;
import cz.morosystems.intellij.plugins.stringfunctions.gui.StringFunctionsDialog;

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

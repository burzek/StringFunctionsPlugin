package sk.mslb.intellij.plugins.stringtools.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class CloseAction extends AbstractAction {

	private ActionsRequestListener actionsRequestListener;

	public CloseAction(ActionsRequestListener actionsRequestListener) {
		this.actionsRequestListener = actionsRequestListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		actionsRequestListener.exitRequested();
	}
}

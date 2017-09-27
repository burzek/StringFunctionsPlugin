package sk.mslb.intellij.plugins.stringfunctions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.DialogBuilder;

import sk.mslb.intellij.plugins.stringfunctions.gui.components.GuiFactory;

/**
 * @author boris.brinza 10-Apr-2017.
 */
public class StringFunctionsAction extends AnAction {


	@Override
	public void actionPerformed(AnActionEvent anActionEvent) {
		GuiFactory guiFactory = new GuiFactory();
		DialogBuilder dlg = guiFactory.createMainDialog(getEventProject(anActionEvent));
		dlg.show();
	}
}

package sk.mslb.intellij.plugins.stringfunctions.gui.actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.*;

import sk.mslb.intellij.plugins.stringfunctions.data.DataProvider;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class CopyToClipboardAction extends AbstractAction {

	private DataProvider dataProvider;
	private UpdateStatusListener updateStatusListener;

	public CopyToClipboardAction(DataProvider dataProvider, UpdateStatusListener updateStatusListener) {
		this.dataProvider = dataProvider;
		this.updateStatusListener = updateStatusListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringSelection stringSelection = new StringSelection(dataProvider.getConversionData().getConvertedText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		updateStatusListener.updateStatus(ResourceKey.COPIED_TO_CLIPBOARD_STATUS);
	}
}

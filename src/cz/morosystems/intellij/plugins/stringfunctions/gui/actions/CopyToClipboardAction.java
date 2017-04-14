package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.*;

import cz.morosystems.intellij.plugins.stringfunctions.data.Document;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class CopyToClipboardAction extends AbstractAction {

	private Document document;

	public CopyToClipboardAction(Document document) {
		this.document = document;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringSelection stringSelection = new StringSelection(document.getConvertedText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}
}

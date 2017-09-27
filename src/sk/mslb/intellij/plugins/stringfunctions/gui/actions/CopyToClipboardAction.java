package sk.mslb.intellij.plugins.stringfunctions.gui.actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import javax.swing.*;

import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKeys;

/**
 * @author boris.brinza 14-Apr-2017.
 */
public class CopyToClipboardAction extends AbstractAction {

	private TransformationProcessor processor;

	public CopyToClipboardAction(TransformationProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringSelection stringSelection = new StringSelection(processor.getTransformationData().getConvertedText());
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
		processor.updateStatus(ResourceKeys.COPIED_TO_CLIPBOARD_STATUS);
	}
}

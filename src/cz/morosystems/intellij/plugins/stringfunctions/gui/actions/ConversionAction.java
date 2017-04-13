package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import cz.morosystems.intellij.plugins.stringfunctions.data.Document;
import cz.morosystems.intellij.plugins.stringfunctions.conversion.ConversionFactory;
import cz.morosystems.intellij.plugins.stringfunctions.conversion.Conversion;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class ConversionAction extends AbstractAction {

	private DocumentProcessor documentProcessor;


	public ConversionAction(DocumentProcessor documentProcessor) {
		this.documentProcessor = documentProcessor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Document document = documentProcessor.getDocument();
		Conversion transformer = new ConversionFactory().getTransformationAction(document.getOperation());
		String str = transformer.convert(document.getInput());
		document.setOutput(str);
		documentProcessor.updateDocument(document);
	}
}

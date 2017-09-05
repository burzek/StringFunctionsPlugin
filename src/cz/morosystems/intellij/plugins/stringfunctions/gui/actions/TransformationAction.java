package cz.morosystems.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import cz.morosystems.intellij.plugins.stringfunctions.conversion.Conversion;
import cz.morosystems.intellij.plugins.stringfunctions.conversion.ConversionFactory;
import cz.morosystems.intellij.plugins.stringfunctions.data.TransformationData;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class TransformationAction extends AbstractAction {

	private TransformationProcessor processor;


	public TransformationAction(TransformationProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TransformationData document = processor.getTransformationData();
		Conversion transformer = new ConversionFactory().getTransformationAction(document.getOperation());
		String str = transformer.convert(document.getOriginalText());
		document.setConvertedText(str);
		processor.updateData(document);
	}
}

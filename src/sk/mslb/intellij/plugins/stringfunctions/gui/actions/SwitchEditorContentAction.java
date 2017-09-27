package sk.mslb.intellij.plugins.stringfunctions.gui.actions;

import java.awt.event.ActionEvent;
import javax.swing.*;

import sk.mslb.intellij.plugins.stringfunctions.conversion.Conversion;
import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionFactory;
import sk.mslb.intellij.plugins.stringfunctions.data.TransformationData;

/**
 * @author boris.brinza 13-Apr-2017.
 */
public class SwitchEditorContentAction extends AbstractAction {

	private TransformationProcessor processor;


	public SwitchEditorContentAction(TransformationProcessor processor) {
		this.processor = processor;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		TransformationData data = processor.getTransformationData();
		data.setOriginalText(data.getConvertedText());
		Conversion transformer = new ConversionFactory().getTransformationAction(data.getOperation());
		String str = transformer.convert(data.getOriginalText());
		data.setConvertedText(str);
		processor.updateData(data);
	}
}

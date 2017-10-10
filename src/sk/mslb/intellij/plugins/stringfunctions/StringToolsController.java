package sk.mslb.intellij.plugins.stringfunctions;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionProcessor;
import sk.mslb.intellij.plugins.stringfunctions.data.ConversionData;
import sk.mslb.intellij.plugins.stringfunctions.data.DataProvider;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.ActionsRequestListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.components.MainPanel;
import sk.mslb.intellij.plugins.stringfunctions.gui.components.StringToolsDialog;

/**
 * @author boris.brinza 10-Oct-2017.
 */
public class StringToolsController implements ActionsRequestListener, DataProvider {

	private StringToolsDialog dialog;
	private ConversionProcessor conversionProcessor;


	public StringToolsController(StringToolsDialog dialog) {
		this.dialog = dialog;
		conversionProcessor = new ConversionProcessor(this);
	}

	public StringToolsDialog getDialog() {
		return dialog;
	}

	@Override
	public ConversionData getConversionData() {
		return new ConversionData(dialog.getOpenedEditor(), getMainPanel().getInputContent(), getMainPanel().getOutputContent(), getMainPanel().getSelectedOperation());
	}

	@Override
	public void exitRequested() {
		dialog.getDialogWrapper().close(0, true);
	}

	@Override
	public void transformationRequested() {
		ConversionData transformationData = conversionProcessor.doConversion();

		getMainPanel().showWarning(transformationData.isInvalidInputFlag());
		getMainPanel().setOutputContent(transformationData.getConvertedText());

	}

	private MainPanel getMainPanel() {
		return dialog.getMainPanel();
	}
}

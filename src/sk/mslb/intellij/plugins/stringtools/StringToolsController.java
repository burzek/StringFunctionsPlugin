package sk.mslb.intellij.plugins.stringtools;

import sk.mslb.intellij.plugins.stringtools.conversion.ConversionProcessor;
import sk.mslb.intellij.plugins.stringtools.data.ConversionData;
import sk.mslb.intellij.plugins.stringtools.data.DataProvider;
import sk.mslb.intellij.plugins.stringtools.gui.actions.ActionsRequestListener;
import sk.mslb.intellij.plugins.stringtools.gui.components.MainPanel;
import sk.mslb.intellij.plugins.stringtools.gui.components.StringToolsDialog;

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

	@Override
	public ConversionData getConversionData() {
		return new ConversionData(dialog.getOpenedEditor(), getMainPanel().getInputContent(), getMainPanel().getOutputContent(),
				getMainPanel().getSelectedOperation());
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

package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.ui.EditorTextField;
import org.jdesktop.swingx.VerticalLayout;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionProcessor;
import sk.mslb.intellij.plugins.stringfunctions.data.ConversionData;
import sk.mslb.intellij.plugins.stringfunctions.data.DataProvider;
import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CloseAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CopyToClipboardAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.ReplaceInEditorAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKey;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class MainPanel extends JPanel implements TransformationRequestListener, DataProvider {
	private GuiFactory guiFactory;

	private StringFunctionsDialog dialog;
	private InputTextEditor inputText;
	private EditorTextField outputText;
	private List<OperationSelector> operations = new ArrayList<>();

	private ConversionProcessor conversionProcessor;

	public MainPanel(StringFunctionsDialog dialog) {
		this.dialog = dialog;
		this.conversionProcessor = new ConversionProcessor(this);
		initalizeGUI();
		loadSelection();

	}

	@Override
	public ConversionData getConversionData() {
		return new ConversionData(dialog.getOpenedEditor(), inputText.getText(), outputText.getText(), getSelectedOperation());
	}

	@Override
	public void transformationRequested() {
		ConversionData transformationData = conversionProcessor.doConversion();
		inputText.showWarning(transformationData.isInvalidInputFlag());
		outputText.setText(transformationData.getConvertedText());
	}

	private void initalizeGUI() {
		guiFactory = new GuiFactory();
		setLayout(new GridBagLayout());

		//original text area
		addComponent(guiFactory.createLabel(ResourceKey.ORIGINAL_TEXT), 0, 0);
		inputText = guiFactory.createInputTextEditor(this);
		addComponent(inputText, 1, 0);

		//converted text area
		addComponent(guiFactory.createLabel(ResourceKey.CONVERTED_TEXT), 0, 1);
		outputText = guiFactory.createEditorTextField();
		addComponent(outputText, 1, 1);

		//conversion actions
		ButtonGroup buttonGroup = new ButtonGroup();
		final JPanel radioPanel = guiFactory.createPanel(new VerticalLayout(0));
		operations.add(guiFactory.createOperationSelector(ResourceKey.STRING_TO_HEX_ACTION, Operation.STRING_TO_HEX, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.HEX_TO_STRING_ACTION, Operation.HEX_TO_STRING, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.STRING_TO_BINARY_ACTION, Operation.STRING_TO_BIN, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.BINARY_TO_STRING_ACTION, Operation.BIN_TO_STRING, this, buttonGroup));
		operations.forEach(radioPanel::add);
		guiFactory.addBorder(radioPanel, ResourceKey.CONVERSION_TITLE);
		addComponent(radioPanel, 0, 3);

		//encoding actions
		final JPanel radioPanel2 = guiFactory.createPanel(new VerticalLayout(0));
		operations.add(guiFactory.createOperationSelector(ResourceKey.BASE_64_ENCODE_ACTION, Operation.BASE_64_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.BASE_64_DECODE_ACTION, Operation.BASE_64_DECODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.URL_ENCODE_ACTION, Operation.URL_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.URL_DECODE_ACTION, Operation.URL_DECODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.HTML_ENCODE_ACTION, Operation.HTML_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKey.HTML_DECODE_ACTION, Operation.HTML_DECODE, this, buttonGroup));
		operations.subList(4, operations.size()).forEach(radioPanel2::add);
		guiFactory.addBorder(radioPanel2, ResourceKey.CODING_TITLE);
		addComponent(radioPanel2, 1, 3);
		operations.get(0).setSelected(true);    //select first button

		//add status line
		StatusLine statusLine = guiFactory.createStatusLine();
		add(statusLine, guiFactory.getGridBagBuilder()
				.withPos(0, 5)
				.withAnchor(GridBagConstraints.CENTER)
				.withGridWidth(2)
				.toGBC());

		//actions
		JPanel buttonPanel = guiFactory.createPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(guiFactory.createActionButton(ResourceKey.REPLACE_ACTION, new ReplaceInEditorAction(this, statusLine)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKey.COPY_TO_CPB_ACTION, new CopyToClipboardAction(this, statusLine)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKey.CLOSE_ACTION, new CloseAction(dialog)));
		add(buttonPanel, guiFactory.getGridBagBuilder()
						.withPos(0, 4)
						.withFill(GridBagConstraints.VERTICAL)
						.withAnchor(GridBagConstraints.WEST)
						.withGridWidth(2)
						.toGBC());

	}

	private void loadSelection() {
		Editor editor = FileEditorManager.getInstance(dialog.getProject()).getSelectedTextEditor();
		if (editor != null) {
			String selectedText = editor.getSelectionModel().getSelectedText();
			if (selectedText != null && selectedText.length() > 0) {
				inputText.setText(selectedText);
			}
		}
	}

	private void addComponent(JComponent componentToAdd, int gridX, int gridY) {
		add(componentToAdd, guiFactory.getGridBagBuilder().withPos(gridX, gridY).toGBC());
	}

	private Operation getSelectedOperation() {
		return operations.stream().filter(AbstractButton::isSelected).findFirst().map(OperationSelector::getOperation)
				.orElseThrow(IllegalStateException::new);
	}
}

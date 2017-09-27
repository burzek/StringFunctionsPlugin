package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.ui.EditorTextField;
import org.jdesktop.swingx.VerticalLayout;

import sk.mslb.intellij.plugins.stringfunctions.conversion.ConversionFactory;
import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.data.TransformationData;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CloseAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CopyToClipboardAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.ReplaceInEditorAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationProcessor;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKeys;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class MainPanel extends JPanel implements TransformationRequestListener, TransformationProcessor {
	private StringFunctionsDialog dialog;

//	private EditorTextField inputText;
	private InputTextEditor inputText;
	private EditorTextField outputText;
	private StatusLine statusLine;
	private List<OperationSelector> operations = new ArrayList<>();


	private GuiFactory guiFactory;

	private Operation operation;


	public MainPanel(StringFunctionsDialog dialog) {
		this.dialog = dialog;
		initalizeGUI();
		loadSelection();


	}

	private void initalizeGUI() {
		guiFactory = new GuiFactory();
		setLayout(new GridBagLayout());

		//original text area
		addComponent(guiFactory.createLabel(ResourceKeys.ORIGINAL_TEXT), 0, 0);
		inputText = guiFactory.createInputTextEditor(this);
		addComponent(inputText, 1, 0);


		//converted text area
		addComponent(guiFactory.createLabel(ResourceKeys.CONVERTED_TEXT), 0, 1);
		outputText = guiFactory.createEditorTextField();
		addComponent(outputText, 1, 1);

//		//convert button
//		GridBagConstraints gbc = guiFactory.getGBC(1, 2);
//		gbc.fill  = GridBagConstraints.VERTICAL;
//		gbc.weightx = 0.1;
//		gbc.anchor = GridBagConstraints.EAST;
//		addComponent(guiFactory.createActionButton(ResourceKeys.CONVERT_ACTION, new TransformationAction(this)), gbc);

//		GridBagConstraints gbc = guiFactory.getGBC(1, 2);
//		gbc.fill  = GridBagConstraints.VERTICAL;
//		gbc.weightx = 0.05;
//		gbc.weighty = 0.05;
//		gbc.anchor = GridBagConstraints.EAST;
//		addComponent(guiFactory.createActionButton(ResourceKeys.SWITCH_EDITORS, new SwitchEditorContentAction(this)), gbc);

		//conversion actions
		ButtonGroup buttonGroup = new ButtonGroup();
		final JPanel radioPanel = guiFactory.createPanel(new VerticalLayout(0));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.STRING_TO_HEX_ACTION, Operation.STRING_TO_HEX, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.HEX_TO_STRING_ACTION, Operation.HEX_TO_STRING, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.STRING_TO_BINARY_ACTION, Operation.STRING_TO_BIN, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.BINARY_TO_STRING_ACTION, Operation.BIN_TO_STRING, this, buttonGroup));
		operations.forEach(s -> radioPanel.add(s));
		guiFactory.addBorder(radioPanel, ResourceKeys.CONVERSION_TITLE);
		addComponent(radioPanel, 0, 3);


		//encoding actions
		final JPanel radioPanel2 = guiFactory.createPanel(new VerticalLayout(0));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.BASE_64_ENCODE_ACTION, Operation.BASE_64_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.BASE_64_DECODE_ACTION, Operation.BASE_64_DECODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.URL_ENCODE_ACTION, Operation.URL_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.URL_DECODE_ACTION, Operation.URL_DECODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.HTML_ENCODE_ACTION, Operation.HTML_ENCODE, this, buttonGroup));
		operations.add(guiFactory.createOperationSelector(ResourceKeys.HTML_DECODE_ACTION, Operation.HTML_DECODE, this, buttonGroup));
		operations.subList(4, operations.size()).forEach(s -> radioPanel2.add(s));
		guiFactory.addBorder(radioPanel2, ResourceKeys.CODING_TITLE);
		addComponent(radioPanel2, 1, 3);

		AbstractButton defaultButton = buttonGroup.getElements().nextElement();
		defaultButton.setSelected(true);	//select first button
//		operation = ((OperationSelectionAction) defaultButton.getAction()).getOperation();

		//actions
		JPanel buttonPanel = guiFactory.createPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.REPLACE_ACTION, new ReplaceInEditorAction(this)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.COPY_TO_CPB_ACTION, new CopyToClipboardAction(this)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.CLOSE_ACTION, new CloseAction(dialog)));
		GridBagConstraints gbc = guiFactory.getGBC(0, 4);
		gbc.fill  = GridBagConstraints.VERTICAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridwidth = 2;
		addComponent(buttonPanel, gbc);

		//add status line
		statusLine = new StatusLine();
		gbc = guiFactory.getGBC(0, 5);
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		addComponent(statusLine, gbc);

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
		add(componentToAdd, guiFactory.getGBC(gridX, gridY));
	}

	private void addComponent(JComponent componentToAdd, GridBagConstraints gbc) {
		add(componentToAdd, gbc);
	}

//
//	@Override
//	public void operationSelected(Operation operation) {
//		this.operation = operation;
//		transformationRequested();
//	}

	@Override
	public void updateData(TransformationData data) {
		inputText.setText(data.getOriginalText());
		outputText.setText(data.getConvertedText());
	}

	@Override
	public TransformationData getTransformationData() {
		return new TransformationData(dialog.getOpenedEditor(), inputText.getText(), outputText.getText(), operation);
	}

	@Override
	public void updateStatus(ResourceKeys status) {
		statusLine.updateStatus(status);
	}

	@Override
	public void transformationRequested() {
		String converted = new ConversionFactory().getTransformationAction(getSelectedOperation()).convert(inputText.getDocument().getText());
		outputText.setText(converted);
	}


	private Operation getSelectedOperation() {
		return operations.stream().filter(AbstractButton::isSelected).findFirst().map(s -> getSelectedOperation()).orElseThrow(IllegalStateException::new);
	}
}

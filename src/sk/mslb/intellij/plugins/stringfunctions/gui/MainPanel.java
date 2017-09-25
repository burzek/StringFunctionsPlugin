package sk.mslb.intellij.plugins.stringfunctions.gui;

import java.awt.*;
import javax.swing.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.ui.EditorTextField;
import org.jdesktop.swingx.VerticalLayout;

import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.data.TransformationData;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CloseAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.CopyToClipboardAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.OperationSelectionAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.OperationSelectionListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.ReplaceInEditorAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationAction;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationProcessor;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class MainPanel extends JPanel implements TransformationProcessor, OperationSelectionListener {
	private StringFunctionsDialog dialog;

	private EditorTextField inputText;
	private EditorTextField outputText;
	private StatusLine statusLine;

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
		inputText = guiFactory.createEditorTextField();
		addComponent(inputText, 1, 0);

		//converted text area
		addComponent(guiFactory.createLabel(ResourceKeys.CONVERTED_TEXT), 0, 1);
		outputText = guiFactory.createEditorTextField();
		addComponent(outputText, 1, 1);

		//convert button
		GridBagConstraints gbc = guiFactory.getGBC(1, 2);
		gbc.fill  = GridBagConstraints.VERTICAL;
		gbc.weightx = 0.1;
		gbc.anchor = GridBagConstraints.EAST;
		addComponent(guiFactory.createActionButton(ResourceKeys.CONVERT_ACTION, new TransformationAction(this)), gbc);

		//conversion actions
		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel radioPanel = guiFactory.createPanel(new VerticalLayout(0));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.STRING_TO_HEX_ACTION, new OperationSelectionAction(this, Operation.STRING_TO_HEX), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.HEX_TO_STRING_ACTION, new OperationSelectionAction(this, Operation.HEX_TO_STRING), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.STRING_TO_BINARY_ACTION, new OperationSelectionAction(this, Operation.STRING_TO_BIN), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BINARY_TO_STRING_ACTION, new OperationSelectionAction(this, Operation.BIN_TO_STRING), buttonGroup));
		guiFactory.addBorder(radioPanel, ResourceKeys.CONVERSION_TITLE);
		addComponent(radioPanel, 0, 3);


		//encoding actions
		radioPanel = guiFactory.createPanel(new VerticalLayout(0));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BASE_64_ENCODE_ACTION, new OperationSelectionAction(this, Operation.BASE_64_ENCODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BASE_64_DECODE_ACTION, new OperationSelectionAction(this, Operation.BASE_64_DECODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.URL_ENCODE_ACTION, new OperationSelectionAction(this, Operation.URL_ENCODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.URL_DECODE_ACTION, new OperationSelectionAction(this, Operation.URL_DECODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.HTML_ENCODE_ACTION, new OperationSelectionAction(this, Operation.HTML_ENCODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.HTML_DECODE_ACTION, new OperationSelectionAction(this, Operation.HTML_DECODE), buttonGroup));
		guiFactory.addBorder(radioPanel, ResourceKeys.CODING_TITLE);
		addComponent(radioPanel, 1, 3);

		AbstractButton defaultButton = buttonGroup.getElements().nextElement();
		defaultButton.setSelected(true);	//select first button
		operation = ((OperationSelectionAction) defaultButton.getAction()).getOperation();

		//actions
		JPanel buttonPanel = guiFactory.createPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.REPLACE_ACTION, new ReplaceInEditorAction(this)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.COPY_TO_CPB_ACTION, new CopyToClipboardAction(this)));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.CLOSE_ACTION, new CloseAction(dialog)));
		gbc = guiFactory.getGBC(0, 4);
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


	@Override
	public void operationSelected(Operation operation) {
		this.operation = operation;
	}

	@Override
	public void updateData(TransformationData data) {
		outputText.setText(data.getConvertedText());
	}

	@Override
	public TransformationData getTransformationData() {
		return new TransformationData(dialog.getOpenedEditor(), inputText.getText(), outputText.getText(), operation);
	}

	@Override
	public void updateStatus(String status) {
		statusLine.updateStatus(status);
	}
}

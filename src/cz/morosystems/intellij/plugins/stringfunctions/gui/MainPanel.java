package cz.morosystems.intellij.plugins.stringfunctions.gui;

import java.awt.*;
import javax.swing.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.ui.EditorTextField;
import org.jdesktop.swingx.VerticalLayout;

import cz.morosystems.intellij.plugins.stringfunctions.gui.actions.ConversionAction;
import cz.morosystems.intellij.plugins.stringfunctions.data.Document;
import cz.morosystems.intellij.plugins.stringfunctions.gui.actions.DocumentProcessor;
import cz.morosystems.intellij.plugins.stringfunctions.gui.actions.SelectAction;
import cz.morosystems.intellij.plugins.stringfunctions.data.Operation;
import cz.morosystems.intellij.plugins.stringfunctions.gui.actions.OperationSelectionListener;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class MainPanel extends JPanel implements DocumentProcessor, OperationSelectionListener {
	private StringFunctionsDialog dialog;

	private EditorTextField inputText;
	private EditorTextField outputText;

	private GuiFactory guiFactory;

	private Operation operation;


	public MainPanel(StringFunctionsDialog dialog) {
		this.dialog = dialog;
		initalizeGUI();
		loadSelection();


	}

	private void initalizeGUI() {
		guiFactory = new GuiFactory(dialog.getProject());
		setLayout(new GridBagLayout());


		addComponent(guiFactory.createLabel(ResourceKeys.INPUT_TEXT), 0, 0);
		inputText = guiFactory.createEditorTextField(ResourceKeys.INPUT_TEXT);
		addComponent(inputText, 1, 0);

		addComponent(guiFactory.createLabel(ResourceKeys.OUTPUT_TEXT), 0, 1);
		outputText = guiFactory.createEditorTextField(ResourceKeys.OUTPUT_TEXT);
		addComponent(outputText, 1, 1);

		GridBagConstraints gbc = guiFactory.getGBC(1, 2);
		gbc.fill  = GridBagConstraints.VERTICAL;
		gbc.weightx = 0.1;
		gbc.anchor = GridBagConstraints.EAST;
		addComponent(guiFactory.createActionButton(ResourceKeys.CONVERT_ACTION, new ConversionAction(this)), gbc);

		ButtonGroup buttonGroup = new ButtonGroup();
		JPanel radioPanel = guiFactory.createPanel(new VerticalLayout(0));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.HEX_TO_STRING_ACTION, new SelectAction(this, Operation.HEX_TO_STRING), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.STRING_TO_HEX_ACTION, new SelectAction(this, Operation.STRING_TO_HEX), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BINARY_TO_STRING_ACTION, new SelectAction(this, Operation.BIN_TO_STRING), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.STRING_TO_BINARY_ACTION, new SelectAction(this, Operation.STRING_TO_BIN), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BASE_64_ENCODE_ACTION, new SelectAction(this, Operation.BASE_64_ENCODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.BASE_64_DECODE_ACTION, new SelectAction(this, Operation.BASE_64_DECODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.URL_ENCODE_ACTION, new SelectAction(this, Operation.URL_ENCODE), buttonGroup));
		radioPanel.add(guiFactory.createRadioButton(ResourceKeys.URL_DECODE_ACTION, new SelectAction(this, Operation.URL_DECODE), buttonGroup));
		addComponent(radioPanel, 0, 3);
		buttonGroup.getElements().nextElement().setSelected(true);	//select first button

		JPanel buttonPanel = guiFactory.createPanel(new FlowLayout(FlowLayout.LEFT));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.REPLACE_ACTION, null));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.COPY_TO_CPB_ACTION, null));
		buttonPanel.add(guiFactory.createActionButton(ResourceKeys.CLOSE_ACTION, null));
		addComponent(buttonPanel, 0, 4);
	}

	@Override
	public void updateDocument(Document document) {
		outputText.setText(document.getOutput());
	}

	@Override
	public Document getDocument() {
		return new Document(inputText.getText(), outputText.getText(), operation);
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
		System.out.println("Selected op:" + operation);
		this.operation = operation;
	}
}

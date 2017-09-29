package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import javax.swing.*;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBLabelDecorator;
import com.intellij.ui.components.JBPanel;

import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKey;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.Resources;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class GuiFactory {

	private Resources resources = new Resources();


	public StringFunctionsDialog createMainDialog(Project project) {
		StringFunctionsDialog dialogBuilder = new StringFunctionsDialog(project);
		dialogBuilder.setCenterPanel(new MainPanel(dialogBuilder));
		dialogBuilder.setTitle(resources.getText(ResourceKey.WINDOW_TITLE));
		dialogBuilder.removeAllActions();
		dialogBuilder.resizable(false);

		return dialogBuilder;
	}


	public void addBorder(JComponent component, ResourceKey title) {
		component.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resources.getText(title)));
	}


	public OperationSelector createOperationSelector(ResourceKey label, Operation operation, TransformationRequestListener requestListener, ButtonGroup buttonGroup) {
		OperationSelector radioButton = new OperationSelector(operation, requestListener);
		radioButton.setText(resources.getText(label));
		addMnemonic(radioButton, label);
		buttonGroup.add(radioButton);
		return radioButton;
	}


	public EditorTextField createEditorTextField() {
		EditorTextField etf = new EditorTextField();
		etf.setOneLineMode(false);
		etf.setPreferredSize(new Dimension(300, 50));
		etf.setMinimumSize(new Dimension(300, 50));
		return etf;
	}

	public InputTextEditor createInputTextEditor(TransformationRequestListener requestListener) {
		return new InputTextEditor(requestListener);
	}


	public JButton createActionButton(ResourceKey label, Action action) {
		JButton button = new JButton(resources.getText(label));
		button.setAction(action);
		button.setText(resources.getText(label));
		addMnemonic(button, label);
		addIcon(button, label);
		return button;
	}

	public JBLabelDecorator createLabel(ResourceKey label) {
		JBLabelDecorator field = JBLabelDecorator.createJBLabelDecorator(resources.getText(label));
		return field;
	}

	public StatusLine createStatusLine() {
		return new StatusLine();
	}

	public JBPanel createPanel(LayoutManager layoutManager) {
		JBPanel panel = new JBPanel(layoutManager);
		return panel;
	}


	public GridBagBuilder getGridBagBuilder() {
		return new GridBagBuilder();
	}


	private void addMnemonic(AbstractButton component, ResourceKey resource) {
		Character mnemonicChar = resources.getMnemonic(resource);
		if (mnemonicChar != null) {
			component.setMnemonic(mnemonicChar);
		}
	}

	private void addIcon(AbstractButton component, ResourceKey resource) {
		Icon icon = resources.getIcon(resource);
		if (icon != null) {
			component.setIcon(icon);
		}

	}



}

package sk.mslb.intellij.plugins.stringfunctions.gui.components;

import java.awt.*;
import javax.swing.*;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBLabelDecorator;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.util.ui.JBUI;

import sk.mslb.intellij.plugins.stringfunctions.data.Operation;
import sk.mslb.intellij.plugins.stringfunctions.gui.actions.TransformationRequestListener;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.ResourceKeys;
import sk.mslb.intellij.plugins.stringfunctions.gui.i18n.Resources;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class GuiFactory {

	private Resources resources = new Resources();

	private GridBagConstraints templateGBC = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH,
			GridBagConstraints.HORIZONTAL, JBUI.insetsRight(5), 0, 0);


	public StringFunctionsDialog createMainDialog(Project project) {
		StringFunctionsDialog dialogBuilder = new StringFunctionsDialog(project);
		dialogBuilder.setCenterPanel(new MainPanel(dialogBuilder));
		dialogBuilder.setTitle(resources.getText(ResourceKeys.WINDOW_TITLE));
		dialogBuilder.removeAllActions();
		dialogBuilder.resizable(false);

		return dialogBuilder;
	}


	public void addBorder(JComponent component, ResourceKeys title) {
		component.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resources.getText(title)));
	}


	public OperationSelector createOperationSelector(ResourceKeys label, Operation operation, TransformationRequestListener requestListener, ButtonGroup buttonGroup) {
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
		InputTextEditor etf = new InputTextEditor(requestListener);
		etf.setOneLineMode(false);
		etf.setPreferredSize(new Dimension(300, 50));
		etf.setMinimumSize(new Dimension(300, 50));
		return etf;
	}


	public JButton createActionButton(ResourceKeys label, Action action) {
		JButton button = new JButton(resources.getText(label));
		button.setAction(action);
		button.setText(resources.getText(label));
		addMnemonic(button, label);
		addIcon(button, label);
		return button;
	}

	public JBLabelDecorator createLabel(ResourceKeys label) {
		JBLabelDecorator field = JBLabelDecorator.createJBLabelDecorator(resources.getText(label));
		return field;
	}

	public JBPanel createPanel(LayoutManager layoutManager) {
		JBPanel panel = new JBPanel(layoutManager);
		return panel;
	}

	public GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = (GridBagConstraints) templateGBC.clone();
		gbc.gridx = x;
		gbc.gridy = y;
		return gbc;
	}


	private void addMnemonic(AbstractButton component, ResourceKeys resource) {
		Character mnemonicChar = resources.getMnemonic(resource);
		if (mnemonicChar != null) {
			component.setMnemonic(mnemonicChar);
		}
	}

	private void addIcon(AbstractButton component, ResourceKeys resource) {
		Icon icon = resources.getIcon(resource);
		if (icon != null) {
			component.setIcon(icon);
		}

	}



}

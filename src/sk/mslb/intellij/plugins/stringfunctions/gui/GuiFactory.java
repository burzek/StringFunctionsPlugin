package sk.mslb.intellij.plugins.stringfunctions.gui;

import java.awt.*;
import javax.swing.*;
import com.intellij.openapi.project.Project;
import com.intellij.ui.EditorTextField;
import com.intellij.ui.components.JBLabelDecorator;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.util.ui.JBUI;

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


	public JBRadioButton createRadioButton(ResourceKeys label, Action action, ButtonGroup buttonGroup) {
		JBRadioButton radioButton = new JBRadioButton(action);
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


	public JButton createActionButton(ResourceKeys label, Action action) {
		JButton button = new JButton(resources.getText(label));
		button.setAction(action);
		button.setText(resources.getText(label));
		addMnemonic(button, label);
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



	private void addMnemonic(AbstractButton component, ResourceKeys label) {
		Character mnemonicChar = resources.getMnemonic(label);
		if (mnemonicChar != null) {
			component.setMnemonic(mnemonicChar);
		}


	}


}

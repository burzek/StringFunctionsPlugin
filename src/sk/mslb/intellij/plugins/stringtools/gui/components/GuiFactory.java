package sk.mslb.intellij.plugins.stringtools.gui.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import com.intellij.openapi.project.Project;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBLabelDecorator;
import com.intellij.ui.components.JBPanel;

import sk.mslb.intellij.plugins.stringtools.data.Operation;
import sk.mslb.intellij.plugins.stringtools.gui.actions.ActionsRequestListener;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.Resources;

/**
 * @author boris.brinza 12-Apr-2017.
 */
public class GuiFactory {

	private Resources resources = new Resources();


	public StringToolsDialog createMainDialog(Project project) {
		StringToolsDialog dialogBuilder = new StringToolsDialog(project);
		dialogBuilder.setTitle(resources.getText(ResourceKey.WINDOW_TITLE));
		return dialogBuilder;
	}


	public void addBorder(JComponent component, ResourceKey title) {
		component.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), resources.getText(title)));
	}


	public OperationSelector createOperationSelector(ResourceKey label, Operation operation, ActionsRequestListener requestListener, ButtonGroup buttonGroup) {
		OperationSelector radioButton = new OperationSelector(operation, requestListener);
		radioButton.setText(resources.getText(label));
		addMnemonic(radioButton, label);
		buttonGroup.add(radioButton);
		return radioButton;
	}


	public JTextArea createOutputTextField() {
		JTextArea etf = new JTextArea();
		//etf.setOneLineMode(false);
		etf.setLineWrap(true);
		etf.setBorder(new LineBorder(JBColor.LIGHT_GRAY));
		etf.setPreferredSize(new Dimension(350, 100));
		etf.setMinimumSize(new Dimension(350, 100));
		return etf;
	}

	public InputTextEditor createInputTextEditor(ActionsRequestListener requestListener) {
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
		return JBLabelDecorator.createJBLabelDecorator(resources.getText(label));
	}

	public StatusLine createStatusLine() {
		return new StatusLine();
	}

	public JBPanel createPanel(LayoutManager layoutManager) {
		return new JBPanel(layoutManager);
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

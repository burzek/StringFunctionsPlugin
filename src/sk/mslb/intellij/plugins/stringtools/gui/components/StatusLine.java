package sk.mslb.intellij.plugins.stringtools.gui.components;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.LineBorder;
import com.intellij.ui.JBColor;

import sk.mslb.intellij.plugins.stringtools.gui.actions.UpdateStatusListener;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.ResourceKey;
import sk.mslb.intellij.plugins.stringtools.gui.i18n.Resources;

/**
 * @author boris.brinza
 */
public class StatusLine extends JPanel implements UpdateStatusListener {

	private JLabel statusLabel;
	private Resources resources = new Resources();

	public StatusLine() {
		initializeGUI();

	}

	private void initializeGUI() {

		setBorder(new LineBorder(JBColor.LIGHT_GRAY));

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		statusLabel = new JLabel(resources.getText(ResourceKey.STATUS_DEFAULT_TEXT));
		statusLabel.setForeground(JBColor.GRAY);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(statusLabel);
	}

	@Override
	public void updateStatus(ResourceKey status) {
		statusLabel.setText(resources.getText(status));
		Executors.newSingleThreadScheduledExecutor().schedule(() -> statusLabel.setText(resources.getText(ResourceKey.STATUS_DEFAULT_TEXT)), 1, TimeUnit.SECONDS);
	}


}

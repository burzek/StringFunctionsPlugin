package sk.mslb.intellij.plugins.stringfunctions.gui;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

/**
 * @author boris.brinza
 */
public class StatusLine extends JPanel {

	private static final String DEFAULT_STATUS = "Ready";
	private JLabel statusLabel;

	public StatusLine() {
		initializeGUI();
	}

	private void initializeGUI() {


		setBorder(new SoftBevelBorder(BevelBorder.LOWERED));

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		statusLabel = new JLabel(DEFAULT_STATUS);
		statusLabel.setForeground(Color.GRAY);
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(statusLabel);

	}

	public void updateStatus(String status) {
		statusLabel.setText(status);
		Executors.newSingleThreadScheduledExecutor().schedule(() -> statusLabel.setText(DEFAULT_STATUS), 3, TimeUnit.SECONDS);
	}
}
